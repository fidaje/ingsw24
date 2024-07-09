import requests
from build_request.request_builder import add_guest, auth

credentials = auth()

url = "http://172.31.6.1:8080/ingsw24/gateway/1/guests"

payload = add_guest()
headers = {
  'Content-Type': 'application/json',
  'Authorization': 'Basic '+credentials
}

response = requests.request("PUT", url, headers=headers, data=payload)

print(response.status_code)


