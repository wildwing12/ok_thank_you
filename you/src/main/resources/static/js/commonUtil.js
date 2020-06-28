//공통 기능 함수화 모음
const IsEmpty = val => {
	if(val == '' || val == null || val == undefined){
		return true;
	}else{
		return false;
	}
}

const IgnoreEmptyValue = val => {
	if(val == '' || val == null || val == undefined){
		return '';
	}else{
		return val;
	}
}