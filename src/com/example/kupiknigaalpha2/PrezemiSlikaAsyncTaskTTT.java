package com.example.kupiknigaalpha2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.ImageView;

public class PrezemiSlikaAsyncTaskTTT extends AsyncTask<String, Integer, Void> {
	Context c;
	ProgressDialog pd;
	File file;
	ImageView iv;
	public PrezemiSlikaAsyncTaskTTT(Context c,ImageView v){
		this.c = c;
		iv = v;
	}
	

	
	@Override
	protected void onPreExecute() {
		pd = new ProgressDialog(c);
		pd.setTitle("Prezemam podatoci");
		pd.setMessage("Prezemam slika");
		pd.setCancelable(false);
		pd.setMax(100);
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pd.show();
		super.onPreExecute();
	}

	@Override
	protected void onPostExecute(Void result) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inSampleSize = 4096;
		Bitmap bmp = BitmapFactory.decodeFile(file.getAbsolutePath());
		Bitmap nbmp = Bitmap.createScaledBitmap(bmp, 100, 150, false);
		iv.setImageBitmap(nbmp);
		pd.dismiss();
		super.onPostExecute(result);
	}


	@Override
	protected void onProgressUpdate(Integer... values) {
		pd.setProgress(values[0]);
		super.onProgressUpdate(values);
	}

	@Override
	protected Void doInBackground(String... url) {
		
		file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"slika.png");
		
		try {
			URL toDownload = new URL(url[0]);
			URLConnection connection = toDownload.openConnection();
			
			connection.connect();
			int fileLenght = connection.getContentLength();
			InputStream input = new BufferedInputStream(toDownload.openStream());
			OutputStream output = new FileOutputStream(file);
			
			byte data[] = new byte[1024];
			long total = 0;
			int count = 0;
			
			while((count = input.read(data))!=-1){
				total +=count;
				publishProgress((int) (total*100/fileLenght));
				
				output.write(data, 0,count);
			}
			output.flush();
			output.close();
			input.close();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return null;
	}

}
