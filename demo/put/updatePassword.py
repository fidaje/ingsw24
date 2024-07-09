import requests
from build_request.request_builder import set_password, auth

credentials = auth()

url = "http://172.31.6.1:8080/ingsw24/gateway/users"

password = set_password()

headers = {
  'Content-Type': 'application/json',
  'Authorization': 'Basic '+credentials
}

response = requests.request("PUT", url, headers=headers, data=password)

print(response.status_code)
