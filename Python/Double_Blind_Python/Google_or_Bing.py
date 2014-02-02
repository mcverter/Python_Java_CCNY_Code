import requests
import re
from bs4 import BeautifulSoup
import random
import os
import datetime

GOOGLE = "http://www.google.com/search?num=50&q="
BING="http://www.bing.com/search?count=50&q="
survey_file=open("survey_results.txt", 'a')
MAX_RESULTS=10


Google_Results = {}
Bing_Results={}
 
def make_presentation_page(lhs, rhs):
    html_page = '''
<html>
<head> 
<title> Which is better </title>
</head>
<body>
<h1> Which Search Results are Better? </h1>
<table>
<thead><tr><th> Left Side </th><th> Right Side </th></thead>
<tr><td> %s </td>



<td> %s </td> </tr>
</table>

''' % (lhs, rhs)
    search_file = open("search_results.tmp.html",  'w')
    search_file.write(html_page)



def do_query(query, lhs):
    google_results = do_google_query(query)
    bing_results = do_bing_query(query)
    if (lhs == 0):
        make_presentation_page(google_results, bing_results)
    else:
        make_presentation_page(bing_results, google_results)
        


def do_google_query(query):
    google_query=GOOGLE + query
    goo_req = requests.get(google_query)
    soup = BeautifulSoup(goo_req.text)
    all_links = soup.find_all("a")
    all_results=""
    Google_Results={}
    numResults=0

    for link in all_links:
        if (numResults>=MAX_RESULTS):
            break
        lunk = str(link)
        if ((re.match("<a href=\"\/url\?q=(http.*?)<\/a>", lunk))):
            if (re.search("webcache\.googleusercontent\.com", lunk) or 
                (re.search("img", lunk))):
                1
            else:
                if not re.search("#", lunk):
                    lunk=re.sub("\/url\?q=", "", lunk)
                    match=re.search("(http:\/\/.*?\/)", lunk)
                    if (match != None):
                        base_url=match.group(1)
                        if (base_url not in Google_Results):
                            lunk=re.sub("&amp.*?\"","\"",lunk)
                            all_results += "\n <li> " + lunk + "</li>"
                            Google_Results[base_url]=1
                            numResults+=1
    return all_results


def do_bing_query(query):
    bing_query=BING + query
    bing_req = requests.get(bing_query)
    soup = BeautifulSoup(bing_req.text)
    all_links = soup.find_all("a")
    Bing_Results={}
    numResults=0
    all_results=""
    for link in all_links:
        if (numResults>=MAX_RESULTS):
            break
        lunk = str(link)
        if ((re.search("href=\"http",  lunk)) and not
            re.search("#", lunk) and not
            re.search("bing", lunk) and not
            re.search("microsoft", lunk) and not
            re.search("msn", lunk)    and not
            re.search("img", lunk)):
            lunk=re.sub("h=\".*?\"",  "",  lunk)            
            lunk=re.sub("\&amp.*?\"","\"",lunk)
            match=re.search("(http:\/\/.*?\/)", lunk)
            if (match != None):
                base_url=match.group(1)
                if (base_url not in Bing_Results):
                    Bing_Results[base_url]=1
                    all_results += "\n <li>" + lunk + " </li>"
                    numResults+=1
    return all_results


def show_page():
    os.system("firefox search_results.tmp.html")
    1

def print_instructions():
    print ("Please rate the search results on a scale from 1 to 9")
    print ("-- 1: Terrible")
    print ("-- 2: Very bad")
    print ("-- 3: Bad")
    print ("-- 4: A little bad")
    print ("-- 5: Average")
    print ("-- 6: A little good")
    print ("-- 7: Good")
    print ("-- 8: Very good")
    print ("-- 9: Excellent")


    
def main():
    query=raw_input("Please enter a query term:  ")
    lhs = random.randint(1,10) %2 
    do_query(query, lhs)
    show_page()
    print_instructions()

    lrating = int(raw_input("How do you rate the Left Side results?"))
    rrating = int(raw_input("How do you rate the Right Side results?"))
    today = datetime.date.today()

    if (lhs):
        survey_file.write("%s, %s, %s, %s\n" % (lrating, rrating, query, datetime.date.today()))
    else:
        survey_file.write("%s, %s, %s, %s\n" % (rrating, lrating, query, datetime.date.today()))


if __name__ == '__main__':
    main()
