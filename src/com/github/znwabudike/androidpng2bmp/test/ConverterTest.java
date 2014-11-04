package com.github.znwabudike.androidpng2bmp.test;


import java.io.IOException;


import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import com.github.znwabudike.alogger.Log;

import com.github.znwabudike.androidpng2bmp.Converter;
import com.github.znwabudike.androidpng2bmp.hackactivity.Hacktivity;


public class ConverterTest extends ActivityInstrumentationTestCase2<Hacktivity>{

	private Converter converter;
	private Hacktivity hacktivity;
	public byte[] bmpByteArray;
	public byte[] pngByteArray;
	private boolean isPNG = true;
	public ConverterTest(){
		super(Hacktivity.class);
	}

	@Override
	protected void setUp() throws Exception{
		super.setUp();
		hacktivity = getActivity();
		converter = new Converter();
	
		
		testGetArray();
		testIsPNGHeader();
		testSave();
	}

	public void testSave() {
		try {
			Context testContext = getInstrumentation().getContext();
			//put byte stream here
			pngByteArray = converter.getArrayFromResource(testContext, R.raw.copy);
			//BMP image in bytes, do what you need to do here
			bmpByteArray = converter.convert(pngByteArray);
			assertTrue(converter.isBMPHeader(bmpByteArray));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void testIsBMHeader(){
		assertTrue(converter.isBMPHeader(bmpByteArray));
	}
	public void testIsPNGHeader(){ 
		assertTrue(converter.isPNGHeader(pngByteArray));
	}
	
	public void testGetArray() throws IOException {
		Context testContext = getInstrumentation().getContext();
		bmpByteArray = converter.getArrayFromResource(testContext, R.raw.bmp);
		log("bmp bytes = " + bytesToHex(bmpByteArray));
		assertNotNull(bmpByteArray);
		
		pngByteArray = converter.getArrayFromResource(testContext, R.raw.copy);
		log("png bytes = " + bytesToHex(pngByteArray));
		assertNotNull(pngByteArray);
		
		assertTrue(testHasEndPNG() != 0);
		
	}

	private int testHasEndPNG() {
		int num = converter.hasEndPNG(pngByteArray);
		log("index of end = " + num);
		return num;
	}

	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
	private void log(String msg) {
		String TAG = ConverterTest.class.getSimpleName();
		if (BuildConfig.DEBUG) {
			Log.d(TAG, msg);
		}
	}
}
