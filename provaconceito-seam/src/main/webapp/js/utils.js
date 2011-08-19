function aplphaLatin(obj){
	var value = jQuery(obj).val();
	if(value != ''){
		jQuery(obj).val(value.replace(/[^a-z0-9 ãçêâõíàá]/gi, ''));
	}
}
