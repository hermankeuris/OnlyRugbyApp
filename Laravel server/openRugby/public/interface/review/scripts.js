function loadHome()
{
	location.reload();
}
function loadMatches()
{
	document.getElementById("header-title").innerHTML = "Matches";
	document.getElementById("content").innerHTML = "matches, retrieve with ajax";
}
function loadTeams()
{
	document.getElementById("header-title").innerHTML = "Teams";
	document.getElementById("content").innerHTML = "teams, retrieve with ajax";
}
function loadTeamPlayers()
{
	document.getElementById("header-title").innerHTML = "Team - Team A";
	document.getElementById("content").innerHTML = "team details, retrieve with ajax";
}
function loadPlayers()
{
	document.getElementById("header-title").innerHTML = "Players";
	alert('Load player page');
}