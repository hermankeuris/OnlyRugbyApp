<?php

namespace App\Http\Controllers;

use DB;
use App\Http\Controllers\Controller;
use Illuminate\Contracts\Routing\ResponseFactory;
use Illuminate\Http\Request;
use JWTAuth;
use Tymon\JWTAuth\Exceptions\JWTException;
use Tymon\JWTAuth\Facades\JWTFactory;

class AjaxController extends Controller
{
    public function teams()
    {
        //response.addHeader("Access-Control-Allow-Origin", "*");
        $teams = DB::table('teams')->get();
        $html = '';
        foreach($teams as $team)
        {
            $html .= "<div class = 'team_div' id='".$team->team_id."' onclick=loadTeam(".$team->team_id.")>".$team->name."</div><br>";
        }
        return $html;
    }
    
    public function team()
    {
        //response.addHeader("Access-Control-Allow-Origin", "*");
        $team = DB::table('teams')->where('team_id',$_GET['team_id'])->first();
        echo "<div class = 'team_div' id='".$team->team_id;
        echo "'> <h3>Team: ".$team->name;
        echo "</h3><p>Players:";
        $players = DB::table('players')->select('player_id','name','surname','curr_position')->where('team_id',$_GET['team_id'])->get();  
        echo "<table >
        <tr style='text-align:left'>
        <th>FirstName</th><th>Surname</th><th>Position</th>
        </tr>";
        foreach ($players as $player)
        {
                echo "<tr class='playerRow' style='text-align:left' onclick='loadPlayer(\"".$player->player_id."\")'>";
                echo "<td>" . $player->name. "</td>";
                echo "<td>" . $player->surname. "</td>";
                echo "<td>" . $player->curr_position. "</td>";
                echo "</tr>";            
        }
        echo "</table></p>";
        echo "</div><br>";
    }
    
    public function upcoming()
    {
        //response.addHeader("Access-Control-Allow-Origin", "*");
        $matches = DB::table('matches')->where('timestamp','>',date("Y-m-d h:i:s"))->get();
        $html = '';
        foreach ($matches as $matchRow)
        {
            $html .= "<div class = 'matchRow' id = '".$matchRow->match_id."' onclick=loadMatch(".$matchRow->match_id.")>";
            $teamARow = DB::table('teams')->where('team_id',$matchRow->team_a_id)->first();
            $teamBRow = DB::table('teams')->where('team_id',$matchRow->team_b_id)->first();
            $html .= "<h3> ".$teamARow->name." VS ".$teamBRow->name."</h3><p></p>";
            $html .= "<p> Scheduled for: ".$matchRow->timestamp." </p>";
            $html .= "</div>";
        }
        return $html;
    }
    
    public function matches()
    {
        //response.addHeader("Access-Control-Allow-Origin", "*");
        $matches = DB::table('matches')->get();
        $html = '';
        foreach ($matches as $matchRow)
        {
            $html .= "<div class = 'matchRow' id = '".$matchRow->match_id."' onclick=loadMatch(".$matchRow->match_id.")>";
            $teamARow = DB::table('teams')->where('team_id',$matchRow->team_a_id)->first();
            $teamBRow = DB::table('teams')->where('team_id',$matchRow->team_b_id)->first();
            $html .= "<h3> ".$teamARow->name." VS ".$teamBRow->name."</h3><p></p>";
            $html .= "<p> Scheduled for: ".$matchRow->timestamp." </p>";
            $html .= "</div>";
        }
        return $html;

    }
    
    public function match()
    {
        //response.addHeader("Access-Control-Allow-Origin", "*");
        $match = DB::table('matches')->where('match_id',$_GET['match_id'])->first();
        $teamARow = DB::table('teams')->where('team_id',$match->team_a_id)->first();
        $teamBRow = DB::table('teams')->where('team_id',$match->team_b_id)->first();
        //this doesn't work, why?
        $html = "<script type='text/javascript'> 
                window.load = function() {
                    var counter = 0;
                    window.setInterval(function() {
                        document.getElementById('".$match->match_id."').click();
                    }, 30000);
                };</script>";
        $html .= "<div class = 'matchRow' id = '".$match->match_id."' onclick=loadMatch(".$match->match_id.")>";
        $html .= "<h3> ".$teamARow->name." VS ".$teamBRow->name."</h3><p></p>";
        $scoreA = $match->score_a;
        if($scoreA<0)$scoreA=0;
        $scoreB = $match->score_b;
        if($scoreB<0)$scoreB=0;
        $html .= "<p> Current score: ".$teamARow->name." [".$scoreA." : ".$scoreB."] ".$teamBRow->name;
        $html .= "</p></div>"; // todo include event list associated with match
        return $html;
    }

    public function players()
    {
        $players;
        if($_GET['team_id']==0)
        {
            $players = DB::table('players')->select('player_id','name','surname','curr_position')->get();
        }
        else
        {
            $players = DB::table('players')->select('player_id','name','surname','curr_position')->where('team_id',$_GET['team_id'])->get();
        }
        $html = '';
        foreach ($players as $player)
        {
            $html .= "<div style='text-align:left; border: 1px solid black' class = 'player' id = '".$player->player_id."'>";
            $html .= "<ul style='list-style-type:none'>";
            $html .= "<li>Name: ".$player->name." ".$player->surname."</li>";
            $html .= "<li>Position: " . $player->curr_position. "</li>";
            $html .= "</ul>";
            $html .= "</div><br>";
        }
        return $html;
    }
    public function player() //todo
    {
        $player = DB::table('players')->select('player_id','name','surname','curr_position')->where('player_id',$_GET['player_id'])->first();
        $html = '';
        $html .= "<div style='text-align:left; border: 1px solid black' class = 'player' id = '".$player->player_id."'>";
        $html .= "<ul style='list-style-type:none'>";
        $html .= "<li>Name: ".$player->name." ".$player->surname."</li>";
        $html .= "<li>Position: " . $player->curr_position. "</li>";
        $html .= "</ul>";
        $html .= "</div><br>";
        return $html;
    }
}