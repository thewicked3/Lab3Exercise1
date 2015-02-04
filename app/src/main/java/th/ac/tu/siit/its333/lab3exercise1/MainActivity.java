package th.ac.tu.siit.its333.lab3exercise1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    int cr = 0;         // Credits
    double gp = 0.0;    // Grade points
    double gpa = 0.0;   // Grade point average

    List<String> listCodes;
    List<Integer> listCredits;
    List<String> listGrades;
    String output  = new String("List of Course\n");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listCodes = new ArrayList<String>();
        listCredits = new ArrayList<Integer>();
        listGrades = new ArrayList<String>();

        //Use listCodes.add("ITS333"); to add a new course code
        //Use listCodes.size() to refer to the number of courses in the list
    }

    public void calculate(){

        cr = 0;
        gp = 0.0;
        gpa = 0.0;

        for(int i=0;i<listCodes.size();i++){
        String s = listGrades.get(i);
            double g;
            if(s.equals("A")){
                g=4.0;
            }else if(s.equals("B+")){
                g=3.5;
            }else if(s.equals("B")){
                g=3.0;
             }else if(s.equals("C+")){
                 g=2.5;
            }else if(s.equals("C")){
                g=2.0;
            }else if(s.equals("D+")){
                g=1.5;
            }else{
                g=0.0;
            }
            cr+=listCredits.get(i);
            gp+=(g*listCredits.get(i));



        }

        gpa = gp /cr;


        TextView tvGP =(TextView)findViewById(R.id.tvGP);
        tvGP.setText(Double.toString(gp));
        TextView tvCR=(TextView)findViewById(R.id.tvCR);
        tvCR.setText(Double.toString(cr));
        TextView tvGPA =(TextView)findViewById(R.id.tvGPA);
        tvGPA.setText(Double.toString(gpa));

    }

    public void buttonClicked(View v) {
        int id =v.getId();
        switch (id){
            case R.id.button2:
                Intent i = new Intent(this, CourseActivity.class);
                startActivityForResult(i,99);
                break;
            case R.id.button4:
                Intent j = new Intent(this, CourseListActivity.class);
                output="List of Course\n";
;                for(int count = 0;count<listCodes.size();count++){
                    output+=(listCodes.get(count)+"(");
                    output+=(listCredits.get(count)+"Crerdit(s) =");
                    output+=(listGrades.get(count)+"\n");

                }

               j.putExtra("toshow",output);

                startActivity(j);
                break;
            case R.id.button:
                  cr = 0;
                 gp = 0.0;
                 gpa = 0.0;

                listCodes = new ArrayList<String>();
                listCredits = new ArrayList<Integer>();
                listGrades = new ArrayList<String>();
                TextView tvGPA =(TextView)findViewById(R.id.tvGPA);
                tvGPA.setText(Double.toString(0.0));
                calculate();
                break;

        }



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == 99) {
            if (resultCode == RESULT_OK) {
                String Name = data.getStringExtra("etCode");
                int cr = data.getIntExtra("etCr", 0);
                String grade = data.getStringExtra("Grade");

                listCodes.add(Name);
                listCredits.add(cr);
                listGrades.add(grade);
                calculate();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
