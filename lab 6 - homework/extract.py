import email, sys
from email import policy
from html.parser import HTMLParser

class Stripper(HTMLParser):
    def __init__(self):
        super().__init__()
        self.reset()
        self.fed = []
    def handle_data(self, d):
        self.fed.append(d)
    def get_data(self):
        return ''.join(self.fed)

msg = email.message_from_binary_file(open(sys.argv[1], 'rb'), policy=policy.default)
html = next((part.get_content() for part in msg.walk() if part.get_content_type() == 'text/html'), '')

s = Stripper()
s.feed(html)
with open('extracted_text.txt', 'w', encoding='utf-8') as f:
    f.write(s.get_data())
