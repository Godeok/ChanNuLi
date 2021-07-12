package kr.co.healthcare.game;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import kr.co.healthcare.R;

public class Game3ActivityGridviewVer extends AppCompatActivity {

    GridView gridView;
    Game3GridViewAdapter adapter;

    ArrayList<Game3Item> items;

/*
    static int max_attempt = 10;
    static int attempt_cnt=0;
    static int first=0, second=0,       //카드에 적힌 숫자
            cardNum1=0, cardNum2=0;    //1-16 카드 중 어떤 카드인지

    TextView tv_count2;
    TextView[] cards = new TextView[16];
    Integer[] Rid_tv_cards = {
            R.id.card1, R.id.card2, R.id.card3, R.id.card4, R.id.card5, R.id.card6, R.id.card7, R.id.card8,
            R.id.card9, R.id.card10, R.id.card11, R.id.card12, R.id.card13, R.id.card14, R.id.card15, R.id.card16,
    };
    int[] check_card = new int[16];

 */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3_gridview_ver);

        gridView = findViewById(R.id.grid_view);

        items = new ArrayList<>();
        items.add(new Game3Item(1, 1));
        items.add(new Game3Item(1, 2));
        items.add(new Game3Item(2, 3));
        items.add(new Game3Item(2, 3));
        items.add(new Game3Item(2, 3));
        items.add(new Game3Item(2, 3));
        items.add(new Game3Item(2, 3));

        adapter = new Game3GridViewAdapter(items, getApplicationContext());

        gridView.setAdapter(adapter);

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                items.remove(position);
                adapter.notifyDataSetChanged();

                Toast.makeText(getApplicationContext(), position+"번째 아이템이 삭제되었습니다", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }




    /*
    @Override
    public void onBackPressed() {
        // AlertDialog 빌더를 이용해 종료시 발생시킬 창을 띄운다
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
        alBuilder.setMessage("종료 시 점수가 저장되지 않습니다.");

        alBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                attempt_cnt = 0;
                //게임이 실행되던 액티비티 종료
                finish();
            }
        });

        alBuilder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });

        alBuilder.setTitle("게임 종료");
        alBuilder.show(); //AlertDialog.Bulider로 만든 AlertDialog 보여줌
    }

     */
}