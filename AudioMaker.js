var AudioMakerManager = require('react-native').NativeModules.AudioMakerManager;

function AudioRecorder(settings) {
    if(settings.filePath) 
        AudioMakerManager.setFilePath(filePath)
    if(settings.bitRate) 
        AudioMakerManager.setBitRate(bitRate)
    if(settings.samplingRate) 
        AudioMakerManager.setSamplingRate(samplingRate)
    if(settings.fileExtension) 
        AudioMakerManager.setFileExtension(fileExtension)
    if(settings.outputFormat) 
        AudioMakerManager.setFileOutputFormat(outputFormat)
    if(settings.encoder) 
        AudioMakerManager.setAudionEncoder(encoder)
};

AudioRecorder.prototype.startAudioRecording = function(successCallback, errorCallback) {
    AudioMakerManager.startAudioRecording((data) => successCallback(data),(error) => errorCallback(error));  
}
AudioRecorder.prototype.stopAudioRecording = function(callback) {
    AudioMakerManager.stopAudioRecording((result) => callback(result));
} 
AudioRecorder.prototype.setFilePath = function(filePath) {
    AudioMakerManager.setFilePath(filePath)
} 
AudioRecorder.prototype.setBitRate = function(bitRate) {
    AudioMakerManager.setBitRate(bitRate)
} 
AudioRecorder.prototype.setSamplingRate = function(samplingRate) {
    AudioMakerManager.setSamplingRate(samplingRate)
} 
AudioRecorder.prototype.setFileExtension = function(fileExtension) {
    AudioMakerManager.setFileExtension(fileExtension)
} 
AudioRecorder.prototype.setFileOutputFormat = function(outputFormat) {
    AudioMakerManager.setFileOutputFormat(outputFormat)
} 
AudioRecorder.prototype.setAudioEncoder = function(encoder) {
    AudioMakerManager.setAudioEncoder(encoder)
} 
module.exports = AudioRecorder;

