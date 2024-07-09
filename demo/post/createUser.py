import requests
import json
from build_request.request_builder import create_user

url = "http://172.31.6.1:8080/ingsw24/gateway/user"

username, password = create_user()

payload = json.dumps({
  "username": username,
  "password": password,
  "roles": [
    "OWNER",
    "GUEST"
  ]
})
headers = {
  'Content-Type': 'application/json'
}

response = requests.request("POST", url, headers=headers, data=payload)

print(response.status_code)