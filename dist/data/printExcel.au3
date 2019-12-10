#include <GUIConstantsEx.au3>
#include <ListViewConstants.au3>
#include <WindowsConstants.au3>
#include <GuiListView.au3>
#include <Array.au3>
#include <File.au3>
#include <Excel.au3>
$file_open_input = FileOpen('data.txt',0+8+128)
$Num_of_line = _FileCountLines('data.txt')
$line = FileReadLine($file_open_input,2)
$inp = StringSplit($line,"|")
$numberOfColumn = UBound( $inp) - 1

Global $array[$Num_of_line]
For $count = 0 to $Num_of_line -1
	$line = FileReadLine($file_open_input,$count+1)
	$inp = StringSplit($line,"|")
	$array[$count] = $line
	;_ArrayDisplay($inp)
	;_Excel_RangeWrite($oWorkbook, $oWorkbook.Activesheet,$inp[1],"A"&$count)
	;_Excel_RangeWrite($oWorkbook, $oWorkbook.Activesheet,$inp[2],"B"&$count)
	;_Excel_RangeWrite($oWorkbook, $oWorkbook.Activesheet,$inp[3],"C"&$count)
	;_Excel_RangeWrite($oWorkbook, $oWorkbook.Activesheet,$inp[4],"D"&$count)
Next
;_ArrayDisplay($array)

$row=$Num_of_line - 1
	$oExcel = _Excel_Open()
	$oWorkbook= _Excel_BookNew($oExcel, 2)
	If $row > -1 Then
		For $g = 0 to $row
			$take = $array[$g]
			$arrayLine = StringSplit($take,"|")
            ; MsgBox(0,'',$take)
			;_ArrayDisplay($arrayLine)
			For $x = 0 to $numberOfColumn - 1
				;_ArrayDisplay($take)
				;$co = _GUICtrlListView_GetColumnCount($ListView1)
				$so = so($x)
				;msgbox(0,'','')
				ConsoleWrite($x+1)
				_Excel_RangeWrite($oWorkbook, $oWorkbook.Activesheet,$arrayLine[$x+1],$so&$g+2)
			Next
		Next
	EndIf

	Func so($d)
	If $d = 0 Then  $so = 'a'
	If $d = 1 Then  $so = 'b'
	If $d = 2 Then  $so = 'c'
	If $d = 3 Then  $so = 'd'
	If $d = 4 Then  $so = 'e'
	If $d = 5 Then  $so = 'f'
	If $d = 6 Then  $so = 'g'
	If $d = 7 Then  $so = 'h'
	If $d = 8 Then  $so = 'i'
	If $d = 9 Then  $so = 'j'
	If $d = 10 Then  $so = 'k'
	If $d = 11 Then  $so = 'l'
	If $d = 12 Then  $so = 'm'
	If $d = 13 Then  $so = 'n'
	If $d = 14 Then  $so = 'o'
	If $d = 15 Then  $so = 'p'
	If $d = 16 Then  $so = 'q'
	If $d = 17 Then  $so = 'r'
	If $d = 18 Then  $so = 's'
	Return $so
EndFunc
		#cs
#ce