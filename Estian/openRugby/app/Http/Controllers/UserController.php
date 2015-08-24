<?php

namespace App\Http\Controllers;

use DB;
use App\Http\Controllers\Controller;
use Illuminate\Contracts\Routing\ResponseFactory;
use JWTAuth;
use Tymon\JWTAuth\Exceptions\JWTException;
use Tymon\JWTAuth\Facades\JWTFactory;

class UserController extends Controller
{
    /**
     * Show a list of all of the application's users.
     *
     * @return Response
     */
    public function index()
    {
        try
        {
            $statusCode = 200;
            $response = 
            [
              'users'  => []
            ];

            $users = DB::table('users')->get();
            foreach ($users as $user)
            {
                $response['users'][] = 
                [
                    'user_id' => $user->user_id,
                    'name' => $user->name,
                    'surname' => $user->surname,
                    'email' => $user->email,
                    'username' => $user->username,
                    'password' => $user->password,
                    'syncsts' => $user->syncsts,
                ];
            }

        }catch (Exception $e){
            $statusCode = 400;
        }finally{
            return response()->json($response, $statusCode);
        }
    }

    public function login()
    {
        try
        {
            $statusCode = 200;
            $response = 
            [
              'error' => 'no_error',
            ];
            $token = '';
            $user = DB::table('users')->where('username',$_POST['username'])->first();
            if($user!=null)
            {
                try
                {
                    // attempt to verify the credentials and create a token for the user
                    if (/*!$token = JWTAuth::attempt(['username' => $_POST['username'], 'password' => $_POST['password']])*/!$user->password == $_POST['password'])
                    {
                        $response['error']='invalid_username_or_password';
                        $statusCode = 401;
                    }
                }
                catch (JWTException $e)
                {
                    // something went wrong whilst attempting to create the token
                    $response['error']='could_not_create_token';
                    $statusCode = 500;
                    return response()->json($response, $statusCode);
                }
            }
        }
        catch (Exception $e)
        {
            $response['error']=$e;
            $statusCode = 400;
            return response()->json($response, $statusCode);
        }
        
        $payload = JWTFactory::make($response);
        $token = JWTAuth::encode($payload);
        //echo $token;
        return response()->json($token,$statusCode);
    }
    public function register()
    {
        $statusCode = 200;
        $response = 
        [
            'user_id' => 0,
            'error' => 'no_error',
        ];

        try
        {
            $user = DB::table('users')->where('username',$_POST['username'])->first();//maybe email?
            if(!$user)
            {
                $id = DB::table('users')->insertGetId(
                array('name' => $_POST['name'],
                'surname' => $_POST['surname'],
                'email' => $_POST['email'],
                'username' => $_POST['username'],
                'password' => $_POST['password'])
                );
                if($id!=0)
                {
                    $response['user_id'] = $id;
                }
            }
            else
            {
                $response['error']='username already in use';
                $statusCode = 500;
            }
        }
        catch(Exception $e)
        {
            $response['error']=$e;
            $statusCode = 500;
        }

        return response()->json($response,$statusCode);
    }
}