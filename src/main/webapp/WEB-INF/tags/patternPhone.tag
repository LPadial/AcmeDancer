<script type="text/javascript">
function patternPhone(){
	var phone = String(document.formActor.phone.value)
	var pattern = new RegExp ("^\\+([1-9][0-9]{0,2}) (\\([1-9][0-9]{0,2}\\)) ([a-zA-Z0-9 -]{4,})$")

	if(!phone.match(pattern)){
		if ( confirm( 'Are you sure?'+phone ) ) {
			return true;
		}else{
			return false;
		}
	}else{
		return true;
	}
}


</script>
