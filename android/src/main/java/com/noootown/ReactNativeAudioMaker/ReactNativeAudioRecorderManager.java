package com.noootown.ReactNativeAudioMaker;
import com.facebook.react.bridge.*;
import com.facebook.react.bridge.Callback;
import android.media.MediaActionSound;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.media.MediaScannerConnection;
import android.media.MediaRecorder;
import android.media.CamcorderProfile;
import android.content.Intent;
import android.content.Context;
import javax.annotation.Nullable;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReactNativeAudioMakerManager extends ReactContextBaseJavaModule {
     private final ReactApplicationContext _reactContext;
     private static final String REACT_CLASS = "AudioMakerManager";
     private MediaRecorder mRecorder = null;
     private String filePath = null;
     private String audioName = null;
     private int bitRate = 128000;
     private int samplingRate = 48000;
     private int outputFormat = MediaRecorder.OutputFormat.MPEG_4;
     private int audioEncoder = MediaRecorder.AudioEncoder.AAC;
     private String fileExtension = "aac";
     private Callback audioRecorderCallback = null;
     private boolean isRecording = false;


    public ReactNativeAudioMakerManager (ReactApplicationContext reactContext) {
        super(reactContext);
        _reactContext = reactContext;      
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @ReactMethod
    public void setFilePath(String filePath) {
        this.filePath=filePath;
    }

    @ReactMethod
    public void setBitRate(int bitRate) {
        this.bitRate=bitRate;
    }

    @ReactMethod
    public void setSamplingRate(int samplingRate) {
        this.samplingRate=samplingRate;
    }

    @ReactMethod
    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    @ReactMethod
    public void setOutputFormat(String format) {
        switch(format){
            case "AAC_ADTS":
                this.outputFormat = MediaRecorder.OutputFormat.AAC_ADTS;
                break;
            case "AMR_NB":
                this.outputFormat = MediaRecorder.OutputFormat.AMR_NB;
                break;
            case "AMR_WB":
                this.outputFormat = MediaRecorder.OutputFormat.AMR_WB;
                break;
            case "DEFAULT":
                this.outputFormat = MediaRecorder.OutputFormat.DEFAULT;
                break;
            case "MPEG_4":
                this.outputFormat = MediaRecorder.OutputFormat.MPEG_4;
                break;
            case "RAW_AMR":
                this.outputFormat = MediaRecorder.OutputFormat.RAW_AMR;
                break;
            case "THREE_GPP":
                this.outputFormat = MediaRecorder.OutputFormat.THREE_GPP;
                break;
            case "WEBM":
                this.outputFormat = MediaRecorder.OutputFormat.WEBM;
                break;
        }
    }

    @ReactMethod
    public void setAudioEncoder(String encoder) {
        switch(encoder){
            case "AAC":
                this.audioEncoder = MediaRecorder.AudioEncoder.AAC;
                break;
            case "AAC_ELD":
                this.audioEncoder = MediaRecorder.AudioEncoder.AAC_ELD;
                break;
            case "AMR_NB":
                this.audioEncoder = MediaRecorder.AudioEncoder.AMR_NB;
                break;
            case "AMR_WB":
                this.audioEncoder = MediaRecorder.AudioEncoder.AMR_WB;
                break;
            case "DEFAULT":
                this.audioEncoder = MediaRecorder.AudioEncoder.DEFAULT;
                break;
            case "HE_AAC":
                this.audioEncoder = MediaRecorder.AudioEncoder.HE_AAC;
                break;
            case "VORBIS":
                this.audioEncoder = MediaRecorder.AudioEncoder.VORBIS;
                break;
        }
    }

    private boolean prepareRecord(final Callback errCallback, String filename) {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(outputFormat);
        mRecorder.setAudioEncoder(audioEncoder);
        mRecorder.setAudioEncodingBitRate(bitRate);
        mRecorder.setAudioSamplingRate(samplingRate);

        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File allpath = new File(path, filePath);
        try{
            path.mkdirs();
            allpath.mkdirs();
            audioName = allpath.getAbsolutePath();
            audioName = audioName + "/" + filename + "." +fileExtension;
        }catch (Exception e) {
            errCallback.invoke("Make directory error! "+e.getMessage());
            return false;
        }

        mRecorder.setOutputFile(audioName);
        try {
            audioRecorder.prepare();
            return true;
        } catch (Exception e) {
            errCallback.invoke("Prepare error! "+e.getMessage());
            return false;
        }           
    }
    @ReactMethod
    public void startRecord(final Callback sucCallback, final Callback errCallback,String filename) {
        if (prepareRecord(errCallback,filename)) {
            try {
                mRecorder.start();
                isRecording = true;
            } catch (final Exception e) {
                errorCallback.invoke("Start error! " + e.getMessage());
            }  
        }
    }

    @ReactMethod
    public void stopRecord(final Callback sucCallback,final Callback errCallback) {     
        if (isRecording) {
            File audioFile = new File(audioName);
            File audioFilePath = audioFile.getAbsolutePath();
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);         
            intent.setData(Uri.fromFile(audioFile));
            _reactContext.sendBroadcast(intent);
            if (mRecorder != null) {
                mRecorder.stop();
                mRecorder.release();
                mRecorder = null;
                isRecording = false;
                sucCallback.invoke("Finish Recording: " + audioName);
            }       
        } else {
            errCallback.invoke("Not even recording!");
        }
    }
}
