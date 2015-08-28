<?php

use Illuminate\Foundation\Testing\WithoutMiddleware;
use Illuminate\Foundation\Testing\DatabaseMigrations;
use Illuminate\Foundation\Testing\DatabaseTransactions;
use GuzzleHttp\Client;

class DatabaseTest extends TestCase
{
    /**
     * A basic functional test example.
     *
     * @return void
     */
    public function testPreGameLoadMatches()
    {
        $client = new Client();
        $interact = 
        [
            'instructs' =>[]
        ];
        $instruct = 
        [
            'instruct' => 'preGameLoad'
        ];
        array_push($interact['instructs'],$instruct);

        $interact = json_encode($interact);
        $response = $client->post('http://127.0.0.1/openrugby/public/index.php/dbInteract',[
        'form_params' => [
                'uid' => 4,
                'interact' => $interact
            ]
        ]);
    
        $body = '';
        $body = $response->getBody();

        $json = json_decode($body,true);
        if ($response->getStatusCode() == 200 && $json['error']=='no_error')
        {
            $this->assertTrue(True);
        }
    }   
}
