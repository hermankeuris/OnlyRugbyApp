<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the controller to call when that URI is requested.
|
*/

Route::get('/', 'UserController@index');
Route::get('teams', 'AjaxController@teams');
Route::get('team', 'AjaxController@team');
Route::get('upcoming', 'AjaxController@upcoming');
Route::get('matches', 'AjaxController@matches');
Route::get('match', 'AjaxController@match');
Route::get('players', 'AjaxController@players');
Route::get('player', 'AjaxController@player');

Route::post('login', array(
    'uses' => 'UserController@login' // the controller action to be used
));
Route::post('register', array(
    'uses' => 'UserController@register' // the controller action to be used
));
Route::post('dbInteract', array(
	'uses' => 'DatabaseController@index'
));