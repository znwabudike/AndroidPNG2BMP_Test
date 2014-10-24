package com.github.znwabudike.androidpng2bmp.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.Resources;
import android.test.ActivityInstrumentationTestCase;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityTestCase;
import android.util.Log;

import com.github.znwabudike.androidpng2bmp.Converter;
import com.github.znwabudike.androidpng2bmp.hackactivity.Hacktivity;


public class ConverterTest extends ActivityInstrumentationTestCase2<Hacktivity>{

	private Converter converter;
	private Hacktivity hacktivity;
	public byte[] byteArray;
	private boolean isPNG;
	public ConverterTest(){
		super(Hacktivity.class);
	}

	@Override
	protected void setUp() throws Exception{
		super.setUp();
		hacktivity = getActivity();
		converter = new Converter();
		//		byte[] array = 
		
		
		testGetArray();
		testIsPNGHeader();
		
	}

	public void testIsBMHeader(){
		assertTrue(converter.isBM(byteArray));
	}
	public void testIsPNGHeader(){ 
		assertTrue(isPNG);
	}
	
	public void testGetArray() throws IOException {
		Context testContext = getInstrumentation().getContext();
		Resources testRes = testContext.getResources();
		InputStream streamIn = (InputStream) testRes.openRawResource(R.raw.copy);
		byteArray = new byte[streamIn.available()];
		for(int i = 0; i < byteArray.length; i++){
			byteArray[i] = (byte) streamIn.read();
		}
		log("length = " + byteArray.length);
		assertNotNull(byteArray);
	}

	private void log(String msg) {
		String TAG = ConverterTest.class.getSimpleName();
		if (BuildConfig.DEBUG) {
			Log.d(TAG, msg);
		}
	}
}
