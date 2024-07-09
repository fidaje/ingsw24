import requests
from build_request.request_builder import delete_food, auth, pantry_to_search

pantry = pantry_to_search()
name = delete_food()
credentials = auth()

url = "http://172.31.6.1:8080/ingsw24/gateway/"+str(pantry)+"/foods/"+name

payload = {}
headers = {
  'Authorization': 'Basic '+credentials
}

response = requests.request("DELETE", url, headers=headers, data=payload)

print(response.text)
