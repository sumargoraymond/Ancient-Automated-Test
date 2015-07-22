ControlFocus("File Upload","","Edit1")

Local $dir = @ScriptDir
Local $file = "\banner.png"
Local $fileDir = $dir & $file
ControlSetText("File Upload", "", "Edit1", $fileDir)

ControlClick("File Upload", "", "Button1")