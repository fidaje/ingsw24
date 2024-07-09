import requests
from build_request.request_builder import food_to_search, pantry_to_search, auth

name = food_to_search()
pantry = pantry_to_search()
credentials = auth()

url = "http://172.31.6.1:8080/ingsw24/gateway/"+str(pantry)+"/foods/"+name

payload = {}
headers = {
  'Authorization': 'Basic '+credentials
}

response = requests.request("GET", url, headers=headers, data=payload)

print("Code: ", response.status_code)

print("Body: \n", response.text)
