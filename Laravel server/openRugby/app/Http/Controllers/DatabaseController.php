<?php

namespace App\Http\Controllers;

use DB;
use App\Http\Controllers\Controller;
use Illuminate\Contracts\Routing\ResponseFactory;
use JWTAuth;
use Tymon\JWTAuth\Exceptions\JWTException;
use Tymon\JWTAuth\Facades\JWTFactory;

class DatabaseController extends Controller
{
        /**
     * Show a list of all of the application's users.
     *
     * @return Response
     */
    public function index()
    {
        $user_id = $_POST['uid'];
        $interact = json_decode($_POST['interact'],true);
        function preGameLoadMatches()
        {
            $response = 
            [
              'error' => 'no_error',
              'matches' => []
            ];
            $match_ids = DB::table('user_link_match')->where('user_id',$_POST['uid'])->get();
            $matches = DB::table('matches')->where('timestamp','>',date("Y-m-d h:i:s"))->get();
            foreach ($matches as $matchRow)
            {
                $match = array();
                $match['match_id'] = $matchRow->match_id;
                $match['start_time'] = $matchRow->timestamp;
                
                $teamARow = DB::table('teams')->where('team_id',$matchRow->team_a_id)->first();
                $teamA = array();
                $teamA['name'] = $teamARow->name;
                $teamA['players'] = DB::table('players')->select('player_id','name','surname','curr_position')->where('team_id',$matchRow->team_a_id)->get();
                $match['teamA']=$teamA;

                $teamBRow = DB::table('teams')->where('team_id',$matchRow->team_b_id)->first();
                $teamB = array();
                $teamB['name'] = $teamBRow->name;
                $teamB['players'] = DB::table('players')->select('player_id','name','surname','curr_position')->where('team_id',$matchRow->team_b_id)->get();
                $match['teamB']=$teamB;
                
                array_push($response['matches'],$match);
            }
            return $response;
        }
        function fixStats()
        {
            return 'default_response';
        }
                
        $instructs = $interact['instructs'];
        foreach ($instructs as $key)
        {
            switch ($key['instruct'])
            {
                case 'preGameLoad':
                    $response = preGameLoadMatches();
                    return json_encode($response);
                    break;
                case 'fixStats':
                    $response = fixStats();
                    return json_encode($response);
                default:
                    $response = 'default';
                    print($response);
                    break;
            }
        }
    }
}