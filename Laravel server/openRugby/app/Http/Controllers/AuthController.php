<?php namespace App\Http\Controllers;

use Auth;
use Illuminate\Routing\Controller;

class AuthController extends Controller {

    /**
     * Handle an authentication attempt.
     *
     * @return Response
     */
    public function authenticate()
    {
        if (Auth::attempt(['JWT' => $JWT, 'password' => $password]))
        {
            return redirect()->intended('dashboard');
        }
        try
        {
            $statusCode = 200;
            $response = 
            [
              'authenticatedFlag' => false;
            ];

            $user = DB::table('users')->where('JWT',$_POST['JWT'])->first();
            if($user!=null)
            {
                $response['user'][] = 
                [
                    'user_id' => $user->users_id,
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
        ;
    }
    }

}