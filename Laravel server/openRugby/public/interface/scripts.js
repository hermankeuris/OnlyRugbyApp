function getCORS(url, success)
{
    var xhr = new XMLHttpRequest();
    xhr.open('GET', url, true);
    xhr.onload = success;
    xhr.send();
    return xhr;
}

function loadHome()
{
	document.getElementById("title-heading").innerHTML = "<h2>Home - Upcoming Matches</h2>";
	getCORS("http://localhost/openrugby/public/index.php/upcoming", function(request)
	{
		var response = request.currentTarget.response || request.target.responseText;
		//console.log(response);
		document.getElementById("content").innerHTML = response;
	});
}

function loadMatches()
{
	document.getElementById("title-heading").innerHTML = "<h2>Matches</h2>";
	getCORS("http://localhost/openrugby/public/index.php/matches", function(request)
	{
		var response = request.currentTarget.response || request.target.responseText;
		//console.log(response);
		document.getElementById("content").innerHTML = response;
	});
}
function loadMatch(id)
{
	if(id == 0) return 0;
	document.getElementById("title-heading").innerHTML = "<h2>Match</h2>";
	getCORS("http://localhost/openrugby/public/index.php/match?match_id="+id, function(request)
	{
		var response = request.currentTarget.response || request.target.responseText;
		//console.log(response);
		//return response;
		document.getElementById("content").innerHTML = response;
	});
}

function loadTeams()
{
	document.getElementById("title-heading").innerHTML = "<h2>Teams</h2>";
	getCORS("http://localhost/openrugby/public/index.php/teams", function(request)
	{
		var response = request.currentTarget.response || request.target.responseText;
		//console.log(response);
		document.getElementById("content").innerHTML = response;
	});
}
function loadTeam(id)
{
	document.getElementById("title-heading").innerHTML = "<h2>Teams</h2>";
	getCORS("http://localhost/openrugby/public/index.php/team?team_id="+id, function(request)
	{
		var response = request.currentTarget.response || request.target.responseText;
		//console.log(response);
		document.getElementById("content").innerHTML = response;
	});
}
function loadPlayers(id)
{
	document.getElementById("title-heading").innerHTML = "<h2>Players</h2>";
	getCORS("http://localhost/openrugby/public/index.php/players?team_id="+id, function(request)
	{
		var response = request.currentTarget.response || request.target.responseText;
		//console.log(response);
		document.getElementById("content").innerHTML = response;
	});
}
function loadPlayers(id)
{
	document.getElementById("title-heading").innerHTML = "<h2>Players</h2>";
	getCORS("http://localhost/openrugby/public/index.php/players?team_id="+id, function(request)
	{
		var response = request.currentTarget.response || request.target.responseText;
		//console.log(response);
		document.getElementById("content").innerHTML = response;
	});
}
function loadPlayer(id)
{
	document.getElementById("title-heading").innerHTML = "<h2>Players</h2>";
	getCORS("http://localhost/openrugby/public/index.php/player?player_id="+id, function(request)
	{
		var response = request.currentTarget.response || request.target.responseText;
		//console.log(response);
		document.getElementById("content").innerHTML = response;
	});
}