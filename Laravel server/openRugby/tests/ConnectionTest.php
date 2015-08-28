<?php

use Illuminate\Foundation\Testing\WithoutMiddleware;
use Illuminate\Foundation\Testing\DatabaseMigrations;
use Illuminate\Foundation\Testing\DatabaseTransactions;

class ConnectionTest extends TestCase
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
    public function testIndex()
    {
        $this->get('/')
             ->seeJson([
                    'created' => true,
                ]);
    }
}
