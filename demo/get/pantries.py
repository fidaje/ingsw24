import requests
from build_request.request_builder import auth

credentials = auth()

url = "http://172.31.6.1:8080/ingsw24/gateway/pantries"

payload = {}
headers = {
  'Authorization': 'Basic '+credentials
}

response = requests.request("GET", url, headers=headers, data=payload)

print("Code: ", response.status_code)

print("Body: \n", response.text)