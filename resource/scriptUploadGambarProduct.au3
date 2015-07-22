ControlFocus("File Upload","","Edit1")

Local $dir = @ScriptDir
Local $file = "\alcatel.jpeg"
Local $fileDir = $dir & $file
ControlSetText("File Upload", "", "Edit1", $fileDir)

ControlClick("File Upload", "", "Button1")