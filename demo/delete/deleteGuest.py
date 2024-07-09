import requests
from build_request.request_builder import delete_guest, auth, pantry_to_search

pantry = pantry_to_search()
guest = delete_guest()
credentials = auth()

url = "http://172.31.6.1:8080/ingsw24/gateway/"+str(pantry)+"/guests/"+guest

payload = {}
headers = {
  'Authorization': 'Basic '+credentials
}

response = requests.request("DELETE", url, headers=headers, data=payload)

print(response.text)
