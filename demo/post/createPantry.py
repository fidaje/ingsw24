import requests
from build_request.request_builder import auth

credentials = auth()

url = "http://172.31.6.1:8080/ingsw24/gateway/pantry"

payload = {}
headers = {
  'Authorization': 'Basic '+credentials
}

response = requests.request("POST", url, headers=headers, data=payload)

print(response.status_code)

print(response.text)
