<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Matches API</title>
</head>

<body>
	<h1>Matches API</h1>

	<ul>
		<li><b>US-01 - Soccer matches import</b><br/><br/>
			[GET] http://localhost:8080/api/third-party-matches/all<br/><br/>

			[PUT] http://localhost:8080/api/matches/update-from-third-party<br/><br/>
			<u>Example</u><br/>

<i>
<pre>
{
   "matches":{
      "match":[
         {
            "id":"1000",
            "league":"Premier League",
            "home":"Arsenal",
            "away":"Chelsea",
            "date":"24 September 2016",
            "home_score":"3",
            "away_score":"0"
         },
         {
            "id":"1001",
            "league":"Premier League",
            "home":"Arsenal",
            "away":"Everton",
            "date":"21 May 2017",
            "home_score":"1",
            "away_score":"3"
         }
      ]
   }
}
</pre></i><br/>

			[GET] http://localhost:8080/api/matches/run-update-from-third-party<br/><br/>

		</li>

		<li><b>US-02 - Soccer matches search</b><br/><br/>
			[GET] http://localhost:8080/api/matches/search<br/><br/>
			<u>Optional parameters</u><br/><br/>
			<i>
				home=homeTeam<br/>
				away=awayTeam<br/>
				start_date=dd/mm/yyyy<br/>
				end_date=dd/mm/yyyy<br/>
				Match_Difficult=[Tough_game, Average_game, Major_win]
			</i><br/><br/>
			<u>Authentication</u><br/><br/>
			<i style="color:#ff0000;">
				HTTP Basic Authentication<br/>
				user: user<br/>
				password: password<br/><br/><br/>
			</i>
		</li>

		<li><b>US-03 - Soccer match information update</b><br/><br/>
			[PUT] http://localhost:8080/api/matches/update<br/><br/>
			<u>Example</u><br/>

<i>
<pre>
{
  "id":1000,
  "league":"Premier League",
  "home":"Arsenal",
  "away":"Chelsea",
  "date":"24/09/2016",
  "home_score":3,
  "away_score":0,
  "Match_Difficult":"Major_win"
}
</pre></i>

			<u>Authentication</u><br/><br/>
			<i style="color:#ff0000;">
				HTTP Basic Authentication<br/>
				user: user<br/>
				password: password<br/>
			</i>

		</li>

	</ul>

</body>

</html>