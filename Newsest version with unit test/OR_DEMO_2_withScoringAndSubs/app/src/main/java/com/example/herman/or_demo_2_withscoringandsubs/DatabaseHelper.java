package com.example.herman.or_demo_2_withscoringandsubs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.herman.or_demo_2_withscoringandsubs.Info.Data;
import com.example.herman.or_demo_2_withscoringandsubs.Info.Match;
import com.example.herman.or_demo_2_withscoringandsubs.Info.Player;
import com.example.herman.or_demo_2_withscoringandsubs.Info.Team;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

	ArrayList<Match> matches;
	private static int chosenMatch = 0;

	//Context
	private static Context context;

	//Singleton handle
	private static Data data;

	// Logcat tag
	private static final String LOG = "DatabaseHelper";

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "onlyrugbyMain";

	// Table Names
	private static final String TABLE_PREGAME = "pregame";
	private static final String TABLE_POSTGAME = "postgame";

	// MATCH Information - column names
	private static final String KEY_MATCH_ID = "match_id";
	private static final String KEY_MATCH_KICKOFF = "kickoff";

	// TEAM Information - column names
	//private static final String KEY_TEAM_ID = "team_id";  //not currently in use TODO: add team_id for later queries back to the Laravel server
	private static final String KEY_TEAM_NAME = "team_name";

	// PLAYER Information - column names
	private static final String KEY_PLAYER_ID = "player_id";
	private static final String KEY_PLAYER_NAME = "name";
	private static final String KEY_PLAYER_SURNAME = "surname";
	private static final String KEY_PLAYER_POSITION = "position";

	// PLAYERS Table Create Statements
	private static final String CREATE_TABLE_PREGAME = "CREATE TABLE " + TABLE_PREGAME + "("
			+ "id" + " INTEGER PRIMARY KEY,"
			+ KEY_MATCH_ID + " INTEGER,"
			+ KEY_MATCH_KICKOFF + " TIMESTAMP,"
			+ KEY_TEAM_NAME + " TEXT,"
			+ KEY_PLAYER_ID + " INTEGER,"
			+ KEY_PLAYER_NAME + " TEXT,"
			+ KEY_PLAYER_SURNAME + " TEXT,"
			+ KEY_PLAYER_POSITION + " INTEGER"
			+ ")";

	public DatabaseHelper(Context context, Data data) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
		this.data = data;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// creating required tables
		db.execSQL(CREATE_TABLE_PREGAME);
		//db.execSQL("DROP TABLE IF EXISTS " + TABLE_PREGAME);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// on upgrade drop older tables
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PREGAME);

		// create new tables
		onCreate(db);
	}

	/**
	 * Parses the JSON string containing newest pre-match info.
	 * @param json - The JSON string returned be the call to the main server to get all the pre-match info.
	 */
	public void getNewestInfo(String json) {
		matches = new ArrayList<Match>();

		//Toast.makeText(context, "Feedback: " + json, Toast.LENGTH_LONG).show();
		try {
			JSONObject jsonObj = new JSONObject(json);
			JSONArray matchObj = jsonObj.getJSONArray("matches");
			JSONArray teamAPlayerArr = null;
			JSONArray teamBPlayerArr = null;
			JSONObject teamAObj = null;
			JSONObject teamBObj = null;

			//Toast.makeText(context, "Length: " + matchObj.length(), Toast.LENGTH_SHORT).show();

			for (int i = 0; i < matchObj.length(); ++i) {
				Match match = new Match();
				Team tA = new Team();
				Team tB = new Team();

				JSONObject matchArr = matchObj.getJSONObject(i);

				match.setId(Integer.parseInt(matchArr.getString("match_id")));
				match.setStartTime(matchArr.getString("start_time"));

				teamAObj = matchArr.getJSONObject("teamA");
				tA.setTeamName(teamAObj.getString("name"));
				teamAPlayerArr = teamAObj.getJSONArray("players");

				for (int j = 0; j < teamAPlayerArr.length(); ++j) { //add the details of each individual player to a teamA Object
					Player player = new Player();
					JSONObject playerArr = teamAPlayerArr.getJSONObject(j);

					player.setId(Integer.parseInt(playerArr.getString("player_id")));
					player.setName(playerArr.getString("name"));
					player.setSurname(playerArr.getString("surname"));
					player.setCurr_position(Integer.parseInt(playerArr.getString("curr_position")));

					if (player.getCurr_position() > 15) {
						player.setReserve(false);
					}
					else {
						player.setReserve(true);
					}

					tA.addPlayer(player);
				}

				teamBObj = matchArr.getJSONObject("teamB");
				tB.setTeamName(teamBObj.getString("name"));
				teamBPlayerArr = teamBObj.getJSONArray("players");

				for (int j = 0; j < teamBPlayerArr.length(); ++j) { //add the details of each individual player to a teamB Object
					Player player = new Player();
					JSONObject playerArr = teamBPlayerArr.getJSONObject(j);

					player.setId(Integer.parseInt(playerArr.getString("player_id")));
					player.setName(playerArr.getString("name"));
					player.setSurname(playerArr.getString("surname"));
					player.setCurr_position(Integer.parseInt(playerArr.getString("curr_position")));

					if (player.getCurr_position() > 15) {
						player.setReserve(false);
					}
					else {
						player.setReserve(true);
					}

					tB.addPlayer(player);
				}

				match.setTeamA(tA);
				match.setTeamB(tB);

				matches.add(match);
			}
		}
		catch (JSONException ex) {
			Toast.makeText(context, "JSON Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
		}
		finally {
			for (int i = 0; i < matches.size(); ++i) {
				matches.get(i).print();
			}

            /*data.setTeamOne(matches.get(0).getTeamA());
            data.setTeamTwo(matches.get(0).getTeamB());

            //Team One's on field players
            for (int i = 0; i < matches.get(0).getTeamA().getOnField().size(); ++i) {
                data.getTeamOne().addPlayer(matches.get(0).getTeamA().getOnFieldPlayer(i));
            }

            //Team One's reserves
            for (int i = 0; i < matches.get(0).getTeamA().getReserves().size(); ++i) {
                data.getTeamOne().getReserves().add(matches.get(0).getTeamA().getReservesPlayer(i));
            }

            //Team Two's on field players
            for (int i = 0; i < matches.get(0).getTeamB().getOnField().size(); ++i) {
                data.getTeamOne().addPlayer(matches.get(0).getTeamB().getOnFieldPlayer(i));
            }

            //Team Two's reserves
            for (int i = 0; i < matches.get(0).getTeamB().getReserves().size(); ++i) {
                data.getTeamOne().getReserves().add(matches.get(0).getTeamB().getReservesPlayer(i));
            }*/

            /*data.setTeamOne(new Team("Pretoria Highschool"));
            data.setTeamTwo(new Team("Bloemfontein Highschool"));

            //Team One's on field players
            data.getTeamOne().addPlayer(new Player("John One", 1, false));
            data.getTeamOne().addPlayer(new Player("John Two", 2, false));
            data.getTeamOne().addPlayer(new Player("John Three", 3, false));
            data.getTeamOne().addPlayer(new Player("John Four", 4, false));
            data.getTeamOne().addPlayer(new Player("John Five", 5, false));
            data.getTeamOne().addPlayer(new Player("John Six", 6, false));
            data.getTeamOne().addPlayer(new Player("John Seven", 7, false));
            data.getTeamOne().addPlayer(new Player("John Eight", 8, false));
            data.getTeamOne().addPlayer(new Player("John Nine", 9, false));
            data.getTeamOne().addPlayer(new Player("John Ten", 10, false));
            data.getTeamOne().addPlayer(new Player("John Eleven", 11, false));
            data.getTeamOne().addPlayer(new Player("John Twelve", 12, false));
            data.getTeamOne().addPlayer(new Player("John Thirteen", 13, false));
            data.getTeamOne().addPlayer(new Player("John Fourteen", 14, false));
            data.getTeamOne().addPlayer(new Player("John Fifteen", 15, false));

            //Team One's reserve players
            data.getTeamOne().addPlayer(new Player("John Sixteen", 16, true));
            data.getTeamOne().addPlayer(new Player("John Seventeen", 17, true));
            data.getTeamOne().addPlayer(new Player("John Eighteen", 18, true));
            data.getTeamOne().addPlayer(new Player("John Nineteen", 19, true));
            data.getTeamOne().addPlayer(new Player("John Twenty", 20, true));
            data.getTeamOne().addPlayer(new Player("John TwentyOne", 21, true));
            data.getTeamOne().addPlayer(new Player("John TwentyTwo", 22, true));

            //Team Two's on field players
            data.getTeamTwo().addPlayer(new Player("Paul One", 1, false));
            data.getTeamTwo().addPlayer(new Player("Paul Two", 2, false));
            data.getTeamTwo().addPlayer(new Player("Paul Three", 3, false));
            data.getTeamTwo().addPlayer(new Player("Paul Four", 4, false));
            data.getTeamTwo().addPlayer(new Player("Paul Five", 5, false));
            data.getTeamTwo().addPlayer(new Player("Paul Six", 6, false));
            data.getTeamTwo().addPlayer(new Player("Paul Seven", 7, false));
            data.getTeamTwo().addPlayer(new Player("Paul Eight", 8, false));
            data.getTeamTwo().addPlayer(new Player("Paul Nine", 9, false));
            data.getTeamTwo().addPlayer(new Player("Paul Ten", 10, false));
            data.getTeamTwo().addPlayer(new Player("Paul Eleven", 11, false));
            data.getTeamTwo().addPlayer(new Player("Paul Twelve", 12, false));
            data.getTeamTwo().addPlayer(new Player("Paul Thirteen", 13, false));
            data.getTeamTwo().addPlayer(new Player("Paul Fourteen", 14, false));
            data.getTeamTwo().addPlayer(new Player("Paul Fifteen", 15, false));

            //Team Two's reserve players
            data.getTeamTwo().addPlayer(new Player("Paul Sixteen", 16, true));
            data.getTeamTwo().addPlayer(new Player("Paul Seventeen", 17, true));
            data.getTeamTwo().addPlayer(new Player("Paul Eighteen", 18, true));
            data.getTeamTwo().addPlayer(new Player("Paul Nineteen", 19, true));
            data.getTeamTwo().addPlayer(new Player("Paul Twenty", 20, true));
            data.getTeamTwo().addPlayer(new Player("Paul TwentyOne", 21, true));
            data.getTeamTwo().addPlayer(new Player("Paul TwentyTwo", 22, true));*/

			SQLiteDatabase db = this.getWritableDatabase();
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_PREGAME);
			db.execSQL(CREATE_TABLE_PREGAME);
			db.close();
			createLocalDB();
		}
	}

	// ------------------------ "pregame" table methods ----------------//

	public void createLocalDB() {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		int match_id;
		String match_kickoff;
		String teamA_name;
		String teamB_name;
		Player currPlayer;

		//Loop through all the matches
		for (int m = 0; m < matches.size(); ++m) {
			match_id = matches.get(m).getId();
			match_kickoff = matches.get(m).getStartTime();
			teamA_name = matches.get(m).getTeamA().getTeamName();
			teamB_name = matches.get(m).getTeamB().getTeamName();

			//Loop through the players of Team A of the current match
			for (int p = 0; p < matches.get(m).getTeamA().getOnField().size(); ++p) {
				currPlayer = matches.get(m).getTeamA().getOnFieldPlayer(p);

				//Add the match information
				values.put(KEY_MATCH_ID, match_id);
				values.put(KEY_MATCH_KICKOFF, match_kickoff);

				//Add the Team information
				values.put(KEY_TEAM_NAME, teamA_name);

				//Add the Player information
				values.put(KEY_PLAYER_ID, currPlayer.getId());
				values.put(KEY_PLAYER_NAME, currPlayer.getName());
				values.put(KEY_PLAYER_SURNAME, currPlayer.getSurname());
				values.put(KEY_PLAYER_POSITION, currPlayer.getCurr_position());

				db.insert(TABLE_PREGAME, null, values);
				values.clear();
			}

			//Select Team B of the current match
			for (int p = 0; p < matches.get(m).getTeamB().getOnField().size(); ++p) {
				currPlayer = matches.get(m).getTeamB().getOnFieldPlayer(p);

				//Add the match information
				values.put(KEY_MATCH_ID, match_id);
				values.put(KEY_MATCH_KICKOFF, match_kickoff);

				//Add the Team information
				values.put(KEY_TEAM_NAME, teamB_name);

				//Add the Player information
				values.put(KEY_PLAYER_ID, currPlayer.getId());
				values.put(KEY_PLAYER_NAME, currPlayer.getName());
				values.put(KEY_PLAYER_SURNAME, currPlayer.getSurname());
				values.put(KEY_PLAYER_POSITION, currPlayer.getCurr_position());

				db.insert(TABLE_PREGAME, null, values);
				values.clear();
			}
		}
	}

	public void printAllMatchInfo() {
		String selectQuery = "SELECT * FROM " + TABLE_PREGAME;

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		int match_id = -1;
		String match_kickoff = "";
		String team_name = "";
		int player_id = -1;
		String player_name = "";
		String player_surname = "";
		int player_position = -1;

		// looping through all rows and adding to print string
		if (c.moveToFirst()) {
			do {
				match_id = c.getInt(c.getColumnIndex(KEY_MATCH_ID));
				match_kickoff = c.getString(c.getColumnIndex(KEY_MATCH_KICKOFF));
				team_name = c.getString(c.getColumnIndex(KEY_TEAM_NAME));
				player_id = c.getInt(c.getColumnIndex(KEY_PLAYER_ID));
				player_name = c.getString(c.getColumnIndex(KEY_PLAYER_NAME));
				player_surname = c.getString(c.getColumnIndex(KEY_PLAYER_SURNAME));
				player_position = c.getInt(c.getColumnIndex(KEY_PLAYER_POSITION));

				System.out.println("Match: " + match_id + " starting at: " + match_kickoff + " - Team: " + team_name + ", Player(" + player_id + "): " + player_name + " " + player_surname + " in position " + player_position);
			} while (c.moveToNext());
		}
	}

	// closing database
	public void closeDB() {
		SQLiteDatabase db = this.getReadableDatabase();
		if (db != null && db.isOpen())
			db.close();
	}
}
