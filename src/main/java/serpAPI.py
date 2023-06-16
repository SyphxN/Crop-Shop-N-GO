from serpapi import GoogleSearch
import json
import requests

url = "https://lh3.googleusercontent.com/dqtSpM9s9HE6AAoFXarzHujxYJO2u1lid5aXzIWRHbCCMf61g2CYMVibwVxmnWJJJta-GxW7VtSQmCGwhcAJIvc"
params = {
  "api_key": "eeb332563bfc822f9412cc8db88139c113f2ea9b05ab11dc38a7028c2ebf52a6",
  "engine": "google_lens",
  "url": url
}

search = GoogleSearch(params)
results = search.get_dict()
with open(r'src\\main\\java\\serpAPI.json','w') as f:
    json.dump(results,f,indent = 4)
print(results['visual_matches'])
