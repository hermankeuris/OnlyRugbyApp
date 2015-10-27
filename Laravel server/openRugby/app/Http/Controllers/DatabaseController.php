<?php

namespace App\Http\Controllers;

use DB;
use App\Http\Controllers\Controller;
use Illuminate\Contracts\Routing\ResponseFactory;
use Illuminate\Http\Request;
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
        $user_id = $_POST['user_id'];
        $token = $_POST['JWT'];
        //echo $token;
        //var_dump($token);
        /*if(!authenticate($token)) //todo bcrypt
        {
            //get token
            //set token
            //parse token
            //match token with that of user where user_id == $user_id
            //$response = 'authentication failed'
            return json_encode($response);
        }*/
        $interact = json_decode($_POST['interact'],true);
        $instructs = $interact['instructs'];
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
        
        function gameTime()
        {
            $interact = json_decode($_POST['interact'],true);
            $instructs = $interact['instructs'];
            $parameters = '';
            foreach ($instructs as $key)
            {
                $parameters = $key['parameters'];
            }
            $t = strtotime($data[4]);
            $start_time_first = strtotime($parameters['start']);
            $start_time_second = strtotime($parameters['start_second']);
            $match = $parameters['match'];
            $overtime_first = strtotime($parameters['overtime_first']);
            $overtime_second = strtotime($parameters['overtime_second']);
            $end_time = strtotime($parameters['end_time']);
            $timestamp = strtotime($parameters['timestamp']);
            
            $update = DB::table('time_log')->insert(
                array('match_id' => $match,
                    'start_time_first' => $start_time_first,
                    'start_time_second' => $start_time_second,
                    'overtime_first' => $overtime_first,
                    'overtime_second' => $overtime_second,
                    'end_time' => $end_time)
                );
            
            $description = "gametime log";//to do
            
            $r = DB::table('event_log')
                            ->where('match_id',$match)
                            ->where('time','>=',$timestamp-(60*2))
                            ->where('description',$description)
                            ->first();
            $active = false;
            if(is_null($r))
            {
                $active = true;
            }
            $eventLog = DB::table('event_log')->insert(
                array('time' => $timestamp,
                'match_id' => $match,
                'description'=> $description,
                'active' => $active)
                );
            if(!is_null($update)&&!is_null($eventLog))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        
        function score()
        {
            $interact = json_decode($_POST['interact'],true);
            $instructs = $interact['instructs'];
            $parameters = '';
            foreach ($instructs as $key)
            {
                $parameters = $key['parameters'];
            }
            //var_dump($parameters);
            $timestamp = $parameters['timestamp']; //now();?
            $match = $parameters['match'];
            $team = $parameters['team'];
            $player = DB::table('players')->where('team_id',$team)->where('curr_position',$parameters['player'])->first();
            $player_id = $player->player_id;
            $score_type = $parameters['type'];
            $conversionFlag = $parameters['conversion']['success'];
            $conversionPlayer = $parameters['conversion']['playerNumber'];
            $assistPlayer = $parameters['assist'];
            $penaltySuccess = $parameters['penaltySuccess'];
            $value = 0;
            switch ($score_type)
            {
                case 'try':
                    $value = 5;
                    if($conversionFlag)
                    {
                        $value = $value + 2;
                    }
                    break;
                case 'penaltyKick':
                    $value = 3;
                    break;
                case 'fieldGoal':
                    $value = 3;
                    break;
                default:
                    $value = 0;
                    break;
            }

            $insert = DB::table('score_log')->insert(
                array('time' => $timestamp,
                'match_id' => $match,
                'team_id' => $team,
                'player_id' => $player_id,
                'description' => $description,
                'conversion_success' => $conversionFlag,
                'conversion_player' => $conversionPlayer,
                'assist_id' => $assistPlayer,
                'penalty_success' => $penaltySuccess,
                'score_value' => $value)
                );

            if($insert == true)
            {
                $matchRow = DB::table('matches')->where('match_id',$match)->first();
                if($team == $matchRow->team_a_id)
                {
                    $update = DB::table('matches')->where('match_id',$match)->increment('score_a', $value);
                }
                else
                {
                    $update = DB::table('matches')->where('match_id',$match)->increment('score_b', $value);
                }
            }
            $description = "score log";//to do
            
            $r = DB::table('event_log')
                            ->where('match_id',$match)
                            ->where('time','>=',$timestamp-(60*2))
                            ->where('description',$description)
                            ->first();
            $active = false;
            if(is_null($r))
            {
                $active = true;
            }
            $eventLog = DB::table('event_log')->insert(
                array('time' => $timestamp,
                'match_id' => $match,
                'description'=> $description,
                'active' => $active)
                );
            if(!is_null($update)&&!is_null($eventLog))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        
        function substitution()
        {
            $interact = json_decode($_POST['interact'],true);
            $instructs = $interact['instructs'];
            $parameters = '';
            foreach ($instructs as $key)
            {
                $parameters = $key['parameters'];
            }
            //var_dump($parameters);
            $timestamp = $parameters['timestamp']; //now();?
            $match = $parameters['match'];
            $team = $parameters['team'];
            $player1 = DB::table('players')->where('team_id',$team)->where('curr_position',$parameters['playerOff'])->first();
            $playerOff = $player1->player_id;
            $player2 = DB::table('players')->where('team_id',$team)->where('curr_position',$parameters['playerOn'])->first();
            $playerOn = $player2->player_id;

            $update = DB::table('substitution_log')->insert(
                array('time' => $timestamp,
                'match_id' => $match,
                'team_id' => $team,
                'player_off' => $playerOff,
                'player_on' => $playerOn)
                );

            $description = "substitution log";//to do
            
            $r = DB::table('event_log')
                            ->where('match_id',$match)
                            ->where('time','>=',$timestamp-(60*2))
                            ->where('description',$description)
                            ->first();
            $active = false;
            if(is_null($r))
            {
                $active = true;
            }
            $eventLog = DB::table('event_log')->insert(
                array('time' => $timestamp,
                'match_id' => $match,
                'description'=> $description,
                'active' => $active)
                );
            if(!is_null($update)&&!is_null($eventLog))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        
        function discipline()
        {
            $interact = json_decode($_POST['interact'],true);
            $instructs = $interact['instructs'];
            $parameters = '';
            foreach ($instructs as $key)
            {
                $parameters = $key['parameters'];
            }
            var_dump($parameters);
            $timestamp = $parameters['timestamp']; //now();?
            $match = $parameters['match'];
            $team = $parameters['team'];
            $player = DB::table('players')->where('team_id',$team)->where('curr_position',$parameters['player'])->first();
            $player_id = $player->player_id;
            $card = $parameters['type'];

            $update = DB::table('discipline_log')->insert(
                array('time' => $timestamp,
                'match_id' => $match,
                'team_id' => $team,
                'player_id' => $player_id,
                'type' => $card)
                );

            $description = "discipline log";//to do
            
            $r = DB::table('event_log')
                            ->where('match_id',$match)
                            ->where('time','>=',$timestamp-(60*2))
                            ->where('description',$description)
                            ->first();
            $active = false;
            if(is_null($r))
            {
                $active = true;
            }
            $eventLog = DB::table('event_log')->insert(
                array('time' => $timestamp,
                'match_id' => $match,
                'description'=> $description,
                'active' => $active)
                );
            if(!is_null($update)&&!is_null($eventLog))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        
        function lineout()
        {
            $interact = json_decode($_POST['interact'],true);
            $instructs = $interact['instructs'];
            $parameters = '';
            foreach ($instructs as $key)
            {
                $parameters = $key['parameters'];
            }
            var_dump($parameters);
            $timestamp = $parameters['timestamp']; //now();?
            $match = $parameters['match'];
            $team = $parameters['team'];
            $player = DB::table('players')->where('team_id',$team)->where('curr_position',$parameters['player'])->first();
            $player_id = $player->player_id;
            $teamWon_id = $parameters['teamWon'];

            $update = DB::table('lineout_log')->insert(
                array('time' => $timestamp,
                'match_id' => $match,
                'team_id' => $team,
                'player_id' => $player_id,
                'team_won_id' => $teamWon_id)
                );

            $description = "lineout log";//to do
            
            $r = DB::table('event_log')
                            ->where('match_id',$match)
                            ->where('time','>=',$timestamp-(60*2))
                            ->where('description',$description)
                            ->first();
            $active = false;
            if(is_null($r))
            {
                $active = true;
            }
            $eventLog = DB::table('event_log')->insert(
                array('time' => $timestamp,
                'match_id' => $match,
                'description'=> $description,
                'active' => $active)
                );
            if(!is_null($update)&&!is_null($eventLog))
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        function ruck()
        {
            $interact = json_decode($_POST['interact'],true);
            $instructs = $interact['instructs'];
            $parameters = '';
            foreach ($instructs as $key)
            {
                $parameters = $key['parameters'];
            }
            var_dump($parameters);
            $timestamp = $parameters['timestamp']; //now();?
            $match = $parameters['match'];
            $team = $parameters['team'];

            $update = DB::table('ruck_log')->insert(
                array('time' => $timestamp,
                'match_id' => $match,
                'team_id' => $team)
                );

            $description = "ruck log";//to do
            
            $r = DB::table('event_log')
                            ->where('match_id',$match)
                            ->where('time','>=',$timestamp-(60*2))
                            ->where('description',$description)
                            ->first();
            $active = false;
            if(is_null($r))
            {
                $active = true;
            }
            $eventLog = DB::table('event_log')->insert(
                array('time' => $timestamp,
                'match_id' => $match,
                'description'=> $description,
                'active' => $active)
                );
            if(!is_null($update)&&!is_null($eventLog))
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        function scrum()
        {
            $interact = json_decode($_POST['interact'],true);
            $instructs = $interact['instructs'];
            $parameters = '';
            foreach ($instructs as $key)
            {
                $parameters = $key['parameters'];
            }
            //var_dump($parameters);
            $timestamp = $parameters['timestamp']; //now();?
            $match = $parameters['match'];
            $team = $parameters['team'];

            $update = DB::table('scrum_log')->insert(
                array('time' => $timestamp,
                'match_id' => $match,
                'team_id' => $team)
                );

            $description = "scrum log";//to do
            
            $r = DB::table('event_log')
                            ->where('match_id',$match)
                            ->where('time','>=',$timestamp-(60*2))
                            ->where('description',$description)
                            ->first();
            $active = false;
            if(is_null($r))
            {
                $active = true;
            }
            $eventLog = DB::table('event_log')->insert(
                array('time' => $timestamp,
                'match_id' => $match,
                'description'=> $description,
                'active' => $active)
                );
            if(!is_null($update)&&!is_null($eventLog))
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        function possession()
        {
            $interact = json_decode($_POST['interact'],true);
            $instructs = $interact['instructs'];
            $parameters = '';
            foreach ($instructs as $key)
            {
                $parameters = $key['parameters'];
            }
            var_dump($parameters);
            $timestamp = $parameters['timestamp']; //now();?
            $match = $parameters['match'];
            $team = $parameters['team'];
            $player = DB::table('players')->where('team_id',$team)->where('curr_position',$parameters['player'])->first();
            $player_id = $player->player_id;

            $update = DB::table('possession_log')->insert(
                array('time' => $timestamp,
                'match_id' => $match,
                'team_id' => $team,
                'player_id' => $player_id)
                );

            $description = "possession log";//to do
            
            $r = DB::table('event_log')
                            ->where('match_id',$match)
                            ->where('time','>=',$timestamp-(60*2))
                            ->where('description',$description)
                            ->first();
            $active = false;
            if(is_null($r))
            {
                $active = true;
            }
            $eventLog = DB::table('event_log')->insert(
                array('time' => $timestamp,
                'match_id' => $match,
                'description'=> $description,
                'active' => $active)
                );
            if(!is_null($update)&&!is_null($eventLog))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
                
        
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
                case 'gametime':
                    $response = gameTime();
                    return json_encode($response);
                case 'score':
                    $response = score();
                    return json_encode($response);
                case 'substitution':
                    $response = substitution();
                    return json_encode($response);
                case 'discipline':
                    $response = discipline();
                    return json_encode($response);
                case 'lineout':
                    $response = lineout();
                    return json_encode($response);
                case 'ruck':
                    $response = ruck();
                    return json_encode($response);
                case 'scrum':
                    $response = scrum();
                    return json_encode($response);
                case 'possession':
                    $response = possession();
                    return json_encode($response);
                default:
                    $response = 'default';
                    print($response);
                    break;
            }
        }
    }
}