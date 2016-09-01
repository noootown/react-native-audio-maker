# react-native-audio-maker
React Native library for audio recording in Android.

## About

This library extends the original feature from
https://github.com/Frosty92/react-native-audio-android , and add some settings to make this library even more powerful.
I used to develop Android, not IOS, so maybe someone can do the IOS work for me XDDD.

##Installation

### 1. npm install

nstall react-native-audio-maker`

### 2. android/settings.gradle
```
include ':react-native-audio-maker', ':app'
project(':react-native-audio-maker').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-audio-maker/android')

```

### 3. android/app/build.gradle
```
dependencies {
    compile project(':react-native-audio-maker')
}

```

### 4. android/app/src/main/java/.../MainApplication.java

```

import com.noootown.ReactNativeAudioMaker.ReactNativeAudioMakerPackage; // <-- ADD

public class MainApplication extends ReactApplication {
    @Override
    protected List<ReactPackage> getPackages() {
        return Arrays.<ReactPackage>asList(
                new MainReactPackage(),
                new ReactNativeAudioMakerPackage() // <-- ADD
        );
    }
```

### 5. android/app/src/main/AndroidManifest.xml

```
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.RECORD_AUDIO" />

```

## API
### 1. Initialize
```
import AudioMaker from 'react-native-audio-maker'
let audioMaker = new AudioMaker({
    filePath: 'noootown',
    bitRate: 128000,
    samplingRate: 48000,
    outputFormat: 'MPEG_4',
    audioEncoder: 'AAC',
    fileExtension: 'aac',
});
```

### 2. Initial parameter
| Prop | Type | Default | Note |
|---|---|---|---|
| filePath | String | null | Required!!!, if filePath === 'noootown', then store audio under '/Document/noootown' |
| bitRate | Integer | 128000 |  |
| samplingRate | Integer | 48000 |  |
| outputFormat | String | 'MPEG_4' | MediaRecorder.OutputFormat.MPEG_4 -> fill in 'MPEG_4' |
| audioEncoder | String | 'AAC' | MediaRecorder.AudioEncoder.AAC -> fill in 'AAC' |
| fileExtension | String | 'aac' | '3gp' or somewhat  |

#### outputFormat:
https://developer.android.com/reference/android/media/MediaRecorder.OutputFormat.html

#### audioEncoder
https://developer.android.com/reference/android/media/MediaRecorder.AudioEncoder.html

### 3. start record
```
audioMaker.startRecord('filename', (success) => {
        console.log(success);
    }, (error) => {
        console.log(error);
    });
```

### 4. stop record
```
audioMaker.stopRecord(
    (success) => console.log(success),
    (error) => console.log(error)
);
```
### 5. set parameter
```
audiomaker.setFilePath(filePath)
audiomaker.setBitRate(bitRate)
audiomaker.setSamplingRate(samplingRate)
audiomaker.setFileExtension(fileExtension)
audiomaker.setFileOutputFormat(outputFormat)
audiomaker.setAudioEncoder(encoder)
```

## License
### ISC
