package com.example.draganddrop;

import javax.xml.datatype.Duration;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;

public class MainActivity extends Activity {

    private Button l_btn1, l_btn2, l_btn3, r_btn1, r_btn2, r_btn3, c_btn1, c_btn2;
    private LinearLayout l_linear1, l_linear2, l_linear3, c_linear1, c_linear2, r_linear1, r_linear2, r_linear3;

    private static final String IMAGEBUTTON_TAG1 = "드래그 이미지1";
    private static final String IMAGEBUTTON_TAG2 = "드래그 이미지2";
    private static final String IMAGEBUTTON_TAG3 = "드래그 이미지3";
    private static final String IMAGEBUTTON_TAG4 = "드래그 이미지4";
    private static final String IMAGEBUTTON_TAG5 = "드래그 이미지5";
    private static final String IMAGEBUTTON_TAG6 = "드래그 이미지6";
    private static final String IMAGEBUTTON_TAG7 = "드래그 이미지7";
    private static final String IMAGEBUTTON_TAG8 = "드래그 이미지8";

    int tempL_Id, tempR_Id;
    Drawable tempL_BG, tempR_BG;
    public Button longClickView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // DragEvent.ACTION_DROP에 적용시킬 큰 메뉴2개
        c_linear1 = (LinearLayout) findViewById(R.id.center_linear1);
        c_linear2 = (LinearLayout) findViewById(R.id.center_linear2);

        // 롱클릭 이동이 가능하게 할 버튼들
        l_btn1 = (Button) findViewById(R.id.left_btn1);
        l_btn2 = (Button) findViewById(R.id.left_btn2);
        l_btn3 = (Button) findViewById(R.id.left_btn3);
        c_btn1 = (Button) findViewById(R.id.center_btn1);
        c_btn2 = (Button) findViewById(R.id.center_btn2);
        r_btn1 = (Button) findViewById(R.id.right_btn1);
        r_btn2 = (Button) findViewById(R.id.right_btn2);
        r_btn3 = (Button) findViewById(R.id.right_btn3);

        // 아이디 값을 제대로 가져가는지 확인 할 ClickListener
        l_btn1.setOnClickListener(new OnClickListener());
        l_btn2.setOnClickListener(new OnClickListener());
        l_btn3.setOnClickListener(new OnClickListener());
        c_btn1.setOnClickListener(new OnClickListener());
        c_btn2.setOnClickListener(new OnClickListener());
        r_btn1.setOnClickListener(new OnClickListener());
        r_btn2.setOnClickListener(new OnClickListener());
        r_btn3.setOnClickListener(new OnClickListener());

        // 롱클릭이벤트의 clipdata에서 필요한 태그
        l_btn1.setTag(IMAGEBUTTON_TAG1);
        l_btn2.setTag(IMAGEBUTTON_TAG2);
        l_btn3.setTag(IMAGEBUTTON_TAG3);
        c_btn1.setTag(IMAGEBUTTON_TAG4);
        c_btn2.setTag(IMAGEBUTTON_TAG5);
        r_btn1.setTag(IMAGEBUTTON_TAG6);
        r_btn2.setTag(IMAGEBUTTON_TAG7);
        r_btn3.setTag(IMAGEBUTTON_TAG8);

        // 롱클릭 이벤트가 가능한 버튼들 리스너 부착
        l_btn1.setOnLongClickListener(new LongClickListener());
        l_btn2.setOnLongClickListener(new LongClickListener());
        l_btn3.setOnLongClickListener(new LongClickListener());
        c_btn1.setOnLongClickListener(new LongClickListener());
        c_btn2.setOnLongClickListener(new LongClickListener());
        r_btn1.setOnLongClickListener(new LongClickListener());
        r_btn2.setOnLongClickListener(new LongClickListener());
        r_btn3.setOnLongClickListener(new LongClickListener());

        // 드래그 이벤트를 받을 큰 메뉴 두 군데 레이아웃
        c_linear1.setOnDragListener(new DragListener());
        c_linear2.setOnDragListener(new DragListener());
    }


    private final class LongClickListener implements OnLongClickListener {

        @Override
        public boolean onLongClick(View v) {
            // TODO Auto-generated method stub

            Toast.makeText(getApplicationContext(), "원하는 곳으로 메뉴를 이동하세요", Toast.LENGTH_SHORT).show();

            ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
            String[] mimeTypes = { ClipDescription.MIMETYPE_TEXT_PLAIN };
            ClipData data = new ClipData(v.getTag().toString(), mimeTypes, item);
            DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);

            v.startDrag(data, shadowBuilder, null, 0);
            longClickView = (Button) v;

            return true;

        }
    }

    class DragListener implements OnDragListener {

        @Override
        public boolean onDrag(View view, DragEvent event) {
            // TODO Auto-generated method stub

            // 드래그 이벤트 시작
            switch (event.getAction()) {

            // 이미지드래그 시작될 때
            case DragEvent.ACTION_DRAG_STARTED:
                Log.d("DragClickListener", "ACTION_DRAG_STARTED");
                return true;
            // 드래그 한 이미지를 옮기려는 지역으로 왔을 때
            case DragEvent.ACTION_DRAG_ENTERED:
                Log.d("DragClickListener", "ACTION_DRAG_ENTERED");
                // view.setBackground(target);
                return true;
            // 드래그 한 이미지가 영역을 빠져 나갈 때
            case DragEvent.ACTION_DRAG_EXITED:
                Log.d("DragClickListener", "ACTION_DRAG_EXITED");
                // view.setBackground(normal);
                return true;
            // 이미지를 드랍했을 때
            case DragEvent.ACTION_DROP:
                Log.d("DragClickListener", "ACTION_DROP");               

                if (view == c_linear1) {                   
                    System.out.println("@@longClcikView참조주소" + longClickView);
                    System.out.println("@@c_linear1참조주소" + c_linear1);

                    tempL_BG = c_btn1.getBackground();
                    tempL_Id = c_btn1.getId();

                    if (longClickView == l_btn1) {
                        c_btn1.setId(longClickView.getId());
                        c_btn1.setBackground(longClickView.getBackground());
                        longClickView.setId(tempL_Id);
                        longClickView.setBackground(tempL_BG);
                    } else if (longClickView == l_btn2) {
                        c_btn1.setId(longClickView.getId());
                        c_btn1.setBackground(longClickView.getBackground());
                        longClickView.setId(tempL_Id);
                        longClickView.setBackground(tempL_BG);
                    } else if (longClickView == l_btn3) {
                        c_btn1.setId(longClickView.getId());
                        c_btn1.setBackground(longClickView.getBackground());
                        longClickView.setId(tempL_Id);
                        longClickView.setBackground(tempL_BG);
                    } else if (longClickView == r_btn1) {
                        c_btn1.setId(longClickView.getId());
                        c_btn1.setBackground(longClickView.getBackground());
                        longClickView.setId(tempL_Id);
                        longClickView.setBackground(tempL_BG);
                    } else if (longClickView == r_btn2) {
                        c_btn1.setId(longClickView.getId());
                        c_btn1.setBackground(longClickView.getBackground());
                        longClickView.setId(tempL_Id);
                        longClickView.setBackground(tempL_BG);
                    } else if (longClickView == r_btn3) {
                        c_btn1.setId(longClickView.getId());
                        c_btn1.setBackground(longClickView.getBackground());
                        longClickView.setId(tempL_Id);
                        longClickView.setBackground(tempL_BG);
                    } else if (longClickView == c_btn1) {
                        return false;
                    } else if (longClickView == c_btn2) {
                        c_btn1.setId(longClickView.getId());
                        c_btn1.setBackground(longClickView.getBackground());
                        longClickView.setId(tempL_Id);
                        longClickView.setBackground(tempL_BG);
                    }
                } else if (view == c_linear2) {

                    tempR_BG = c_btn2.getBackground();
                    tempR_Id = c_btn2.getId();

                    if (longClickView == l_btn1) {
                        c_btn2.setId(longClickView.getId());
                        c_btn2.setBackground(longClickView.getBackground());
                        longClickView.setId(tempR_Id);
                        longClickView.setBackground(tempR_BG);
                    } else if (longClickView == l_btn2) {
                        c_btn2.setId(longClickView.getId());
                        c_btn2.setBackground(longClickView.getBackground());
                        longClickView.setId(tempR_Id);
                        longClickView.setBackground(tempR_BG);
                    } else if (longClickView == l_btn3) {
                        c_btn2.setId(longClickView.getId());
                        c_btn2.setBackground(longClickView.getBackground());
                        longClickView.setId(tempR_Id);
                        longClickView.setBackground(tempR_BG);
                    } else if (longClickView == r_btn1) {
                        c_btn2.setId(longClickView.getId());
                        c_btn2.setBackground(longClickView.getBackground());
                        longClickView.setId(tempR_Id);
                        longClickView.setBackground(tempR_BG);
                    } else if (longClickView == r_btn2) {
                        c_btn2.setId(longClickView.getId());
                        c_btn2.setBackground(longClickView.getBackground());
                        longClickView.setId(tempR_Id);
                        longClickView.setBackground(tempR_BG);
                    } else if (longClickView == r_btn3) {
                        c_btn2.setId(longClickView.getId());
                        c_btn2.setBackground(longClickView.getBackground());
                        longClickView.setId(tempR_Id);
                        longClickView.setBackground(tempR_BG);
                    } else if (longClickView == c_btn1) {
                        c_btn2.setId(longClickView.getId());
                        c_btn2.setBackground(longClickView.getBackground());
                        longClickView.setId(tempR_Id);
                        longClickView.setBackground(tempR_BG);
                    } else if (longClickView == c_btn2) {
                        return false;
                    }
                }

                return true;

            case DragEvent.ACTION_DRAG_ENDED:
                Log.d("DragClickListener", "ACTION_DRAG_ENDED");
                break;

            default:
                break;

            }
            return true;
        }

    }

    private final class OnClickListener implements android.view.View.OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
            case R.id.left_btn1:
                Toast.makeText(getApplicationContext(), "공항", Toast.LENGTH_SHORT).show();
                break;
            case R.id.left_btn2:
                Toast.makeText(getApplicationContext(), "호텔", Toast.LENGTH_SHORT).show();
                break;
            case R.id.left_btn3:
                Toast.makeText(getApplicationContext(), "관광", Toast.LENGTH_SHORT).show();
                break;
            case R.id.center_btn1:
                Toast.makeText(getApplicationContext(), "상점", Toast.LENGTH_SHORT).show();
                break;
            case R.id.center_btn2:
                Toast.makeText(getApplicationContext(), "식당", Toast.LENGTH_SHORT).show();
                break;
            case R.id.right_btn1:
                Toast.makeText(getApplicationContext(), "인사,소개", Toast.LENGTH_SHORT).show();
                break;
            case R.id.right_btn2:
                Toast.makeText(getApplicationContext(), "길찾기", Toast.LENGTH_SHORT).show();
                break;
            case R.id.right_btn3:
                Toast.makeText(getApplicationContext(), "예약", Toast.LENGTH_SHORT).show();
                break;
                default:
                    break;
            }

        }

    }

}
