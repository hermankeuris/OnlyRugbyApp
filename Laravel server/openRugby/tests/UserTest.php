<?php

use Illuminate\Foundation\Testing\WithoutMiddleware;
use Illuminate\Foundation\Testing\DatabaseMigrations;
use Illuminate\Foundation\Testing\DatabaseTransactions;
use GuzzleHttp\Client;

class UserTest extends TestCase
{
    /**
     * A basic functional test example.
     *
     * @return void
     */
    /*public function testBasicExample()
    {
        $this->visit('/')
             ->see('Laravel 5');
    }*/
    public function testLogin()
    {
        $client = new Client();
        $response = $client->post('http://127.0.0.1/openrugby/public/index.php/login',[
        'form_params' => [
                'username' => 'admin',
                'password'=>'admin'
            ]
        ]);
    
        $body = '';
        $body = $response->getBody();

        $json = json_decode($body,true);

        if ($response->getStatusCode() == 200 && $json['error']=='no_error' && $json['JWTCreated']==true)
        {
            $this->assertTrue(True);
        }
    }   
    
    public function testRegister()
    {
        $client = new Client();
        $response = $client->post('http://127.0.0.1/openrugby/public/index.php/login',[
        'form_params' => [
            'username' => 'testerman',
            'password'=>'testerman',
            'email'=>'testerman@test.com',
            'name'=>'tester',
            'surname'=>'testerman'
            ]
        ]);
    
        $body = '';
        $body = $response->getBody();

        $json = json_decode($body,true);

        if ($response->getStatusCode() == 200 && $json['error'] =='no_error')
        {
            $this->assertTrue(True);
        }
        elseif ($response->getStatusCode() != 200)
        {
            echo 'Error: '+$json['error'];
        }
    }
}
