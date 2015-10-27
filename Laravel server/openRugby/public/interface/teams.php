<?php
$html = "default";
for($team = 1; $team <=5; $team++)
{
	$html += "<div class = 'team_div' id='team "+$team+"'>"+$team+"</div>";
	
}
echo $html;
?>