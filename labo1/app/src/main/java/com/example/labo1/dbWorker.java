package com.example.labo1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class dbWorker extends AsyncTask
{
    private Context c;
    private AlertDialog ad;
    public dbWorker(Context c)
    {
        this.c=c;
    }
    public String generale="";
    int variableId;
    @Override
    protected void onPreExecute()
    {
        this.ad = new AlertDialog.Builder(this.c).create();
        this.ad.setTitle("Login Statuts");
    }

    @Override
    protected String doInBackground(Object[] param) {

      // String etat=(String) param[0];
        String etat="";
       // generale=(String)param[0];
        if(etat.equals("log"))
        {
            String cible="http://192.168.182.1/andro01/login.php";
            try{
                URL url = new URL(cible);
                HttpURLConnection con=(HttpURLConnection)url.openConnection();
                con.setDoInput(true);
                con.setDoOutput(true);
                con.setRequestMethod("POST");

                OutputStream outs = con.getOutputStream();
                BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(outs,"utf-8"));

                String msg = URLEncoder.encode("user","utf-8")+"="+
                        URLEncoder.encode((String)param[5],"utf-8")+"&"+
                        URLEncoder.encode("pw","utf-8")+"="+
                        URLEncoder.encode((String)param[6],"utf-8");
                bufw.write (msg);
                bufw.flush();
                bufw.close();
                outs.close();


                InputStream ins = con.getInputStream();
                BufferedReader bufr = new BufferedReader(new InputStreamReader(ins,"iso-8859-1"));
                String line;
                StringBuffer sbuff=new StringBuffer();

                while((line=bufr.readLine())!=null)
                {
                    sbuff.append(line);
                }
                return sbuff.toString();
            }
            catch (Exception ex)
            {
                return   ex.getMessage();
            }
        }
        ///////////////////////////////////////////////////////////////////////////////
        else if (etat.equals("insc"))
        {
            String cible="http://192.168.182.1/andro01/inscription.php";
            try{
                URL url = new URL(cible);
                HttpURLConnection con=(HttpURLConnection)url.openConnection();
                con.setDoInput(true);
                con.setDoOutput(true);
                con.setRequestMethod("POST");

                OutputStream outs = con.getOutputStream();
                BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(outs,"utf-8"));

                String msg = URLEncoder.encode("nom","utf-8")+"="+
                        URLEncoder.encode((String)param[1],"utf-8")+"&"+
                        URLEncoder.encode("prenom","utf-8")+"="+
                        URLEncoder.encode((String)param[2],"utf-8")
                        +"&"+
                        URLEncoder.encode("user","utf-8")+"="+
                        URLEncoder.encode((String)param[3],"utf-8")
                        +"&"+
                        URLEncoder.encode("pw","utf-8")+"="+
                        URLEncoder.encode((String)param[4],"utf-8");
                bufw.write (msg);
                bufw.flush();
                bufw.close();
                outs.close();


                InputStream ins = con.getInputStream();
                BufferedReader bufr = new BufferedReader(new InputStreamReader(ins,"iso-8859-1"));
                String line;
                StringBuffer sbuff=new StringBuffer();

                while((line=bufr.readLine())!=null)
                {
                    sbuff.append(line+"\n");
                }

                if(sbuff.toString()=="Compte existe dejas"+"\n")
                {
                    String stat="compte existe";
                    return stat;
                }
                else
                {
                    return sbuff.toString();
                }
            }
            catch (Exception ex)
            {
                return   ex.getMessage();
            }
        }
        ///////////////////////////////////////////////////////////////
        else if(etat.equals("selectall"))
        {
            String cible="http://192.168.0.126/ActiviteScolaire/selectall.php";
            variableId=(int)param[1];
            try{
                URL url = new URL(cible);
                HttpURLConnection con=(HttpURLConnection)url.openConnection();
                con.setDoInput(true);
                con.setDoOutput(true);
                con.setRequestMethod("POST");

                OutputStream outs = con.getOutputStream();
                BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(outs,"utf-8"));
                String msg = URLEncoder.encode("id","utf-8")+"="+
                        URLEncoder.encode(param[1].toString(),"utf-8");

                bufw.write (msg);
                bufw.flush();
                bufw.close();
                outs.close();


                InputStream ins = con.getInputStream();
                BufferedReader bufr = new BufferedReader(new InputStreamReader(ins,"iso-8859-1"));
                String line;
                StringBuffer sbuff=new StringBuffer();

                while((line=bufr.readLine())!=null)
                {
                    sbuff.append(line);
                }

                return sbuff.toString();
            }catch (Exception ex)
            {
                return  ex.getMessage();
            }
        }
        else if(etat.equals("udpdate"))
        {
            String cible="http://192.168.0.126/andro01/selectEt.php";
            variableId=(int)param[1];
            try{
                URL url = new URL(cible);
                HttpURLConnection con=(HttpURLConnection)url.openConnection();
                con.setDoInput(true);
                con.setDoOutput(true);
                con.setRequestMethod("POST");

                OutputStream outs = con.getOutputStream();
                BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(outs,"utf-8"));
                String msg = URLEncoder.encode("id","utf-8")+"="+
                        URLEncoder.encode(param[1].toString(),"utf-8");

                bufw.write (msg);
                bufw.flush();
                bufw.close();
                outs.close();


                InputStream ins = con.getInputStream();
                BufferedReader bufr = new BufferedReader(new InputStreamReader(ins,"iso-8859-1"));
                String line;
                StringBuffer sbuff=new StringBuffer();

                while((line=bufr.readLine())!=null)
                {
                    sbuff.append(line);
                }

                return sbuff.toString();
            }catch (Exception ex)
            {
                return  ex.getMessage();
            }
        }


        return null;

    }
    @Override
    protected void onPostExecute(Object o)
    {
        String valeur = (String)o;
        String intru="intru"+"\n";
        String validation="ok"+"\n";
        int idvar;
        String []splitArray=valeur.split("-");
        String val=splitArray[0];
        String id=splitArray[1];

        if(valeur.equals(intru))
        {
            this.ad.setMessage("Verifiez votre user ou votre password");
            this.ad.show();
            Intent intent = new Intent(c, MainActivity.class);
            c.startActivity(intent);
            ((Activity)c).finish();
        }
        else if(valeur.equals(validation) || valeur.equals("Compte existe dejas"+"\n"))
        {
            this.ad.setMessage("Inscription ok"+(String)o);
            this.ad.show();
            Intent intent = new Intent(c, MainActivity.class);
            c.startActivity(intent);
            ((Activity)c).finish();
        }
        else if(generale.equals("selectall"))
        {
            String []splitArrayInfo=valeur.split("-");
            String nom=splitArrayInfo[0];
            String prenom = splitArrayInfo[1];
            String identifiant = splitArrayInfo[2];
            String pw = splitArrayInfo[3];
            idvar=variableId;
            Intent intent = new Intent(c, EtudiantInformation.class);
            intent.putExtra("name",nom);
            intent.putExtra("prenom",prenom);
            intent.putExtra("identifiant",identifiant);
            intent.putExtra("pw",pw);
            intent.putExtra("id",idvar);
            c.startActivity(intent);
        }
        else
        {
            this.ad.setMessage(valeur);
            this.ad.show();

            Intent intent = new Intent(c, Etudiant.class);
            intent.putExtra("nom",val);
            intent.putExtra("id",id);
            c.startActivity(intent);
            //((Activity)c).finish();

            /*Intent Facteur = new Intent(this,Main2Activity.class);
            Facteur.putExtra("mesage",msg.getText().toString());
            startActivity(Facteur);*/
        }
    }
}
