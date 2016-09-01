var AudioMakerManager = require('react-native').NativeModules.ReactNativeAudioMaker

function AudioMaker(setting) {
    if(setting === null)
        return
    if(setting.filePath !== undefined)
        AudioMakerManager.setFilePath(setting.filePath)
    if(setting.bitRate !== undefined)
        AudioMakerManager.setBitRate(setting.bitRate)
    if(setting.samplingRate !== undefined)
        AudioMakerManager.setSamplingRate(setting.samplingRate)
    if(setting.fileExtension !== undefined)
        AudioMakerManager.setFileExtension(setting.fileExtension)
    if(setting.outputformat !== undefined)
        AudioMakerManager.setFileOutputFormat(setting.outputFormat)
    if(setting.encoder !== undefined)
        AudioMakerManager.setAudionEncoder(setting.encoder)
};

AudioMaker.prototype.startRecord = function(sucCallback, errCallback, filename) {
    AudioMakerManager.startRecord(filename, (data) => sucCallback(data),(error) => errCallback(error))  
}
AudioMaker.prototype.stopRecord = function(sucCallback, errCallback) {
    AudioMakerManager.stopRecord((data) => sucCallback(data),(error) => errCallback(error))
}
AudioMaker.prototype.setFilePath = function(filePath) {
    AudioMakerManager.setFilePath(filePath)
}
AudioMaker.prototype.setBitRate = function(bitRate) {
    AudioMakerManager.setBitRate(bitRate)
}
AudioMaker.prototype.setSamplingRate = function(samplingRate) {
    AudioMakerManager.setSamplingRate(samplingRate)
}
AudioMaker.prototype.setFileExtension = function(fileExtension) {
    AudioMakerManager.setFileExtension(fileExtension)
}
AudioMaker.prototype.setFileOutputFormat = function(outputFormat) {
    AudioMakerManager.setFileOutputFormat(outputFormat)
}
AudioMaker.prototype.setAudioEncoder = function(encoder) {
    AudioMakerManager.setAudioEncoder(encoder)
}

AudioMaker.prototype.setAudioMaker = function(setting) {
    if(setting === null)
        return
    if(setting.filePath !== undefined)
        AudioMakerManager.setFilePath(setting.filePath)
    if(setting.bitRate !== undefined)
        AudioMakerManager.setBitRate(setting.bitRate)
    if(setting.samplingRate !== undefined)
        AudioMakerManager.setSamplingRate(setting.samplingRate)
    if(setting.fileExtension !== undefined)
        AudioMakerManager.setFileExtension(setting.fileExtension)
    if(setting.outputformat !== undefined)
        AudioMakerManager.setFileOutputFormat(setting.outputFormat)
    if(setting.encoder !== undefined)
        AudioMakerManager.setAudionEncoder(setting.encoder)
}
module.exports = AudioMaker

