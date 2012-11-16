var abc = 1;

aa(1);
var zz;

function pointer(val){
	abc = val;
}

function aa(test){
	var def = test;
	bb(def);
	function bb(test2){
		pointer(5);
		return 1;
	}
}

console.log(abc);

