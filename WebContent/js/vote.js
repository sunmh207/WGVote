   $(function() {
    $( "#tabs" ).tabs().addClass( "ui-tabs-vertical ui-helper-clearfix" );
	$( "#tabs1" ).tabs().addClass( "ui-tabs-vertical ui-helper-clearfix" );
	$( "#tabs2" ).tabs().addClass( "ui-tabs-vertical ui-helper-clearfix" );
    $( "#tabs li" ).removeClass( "ui-corner-top" ).addClass( "ui-corner-left" );
  });
 
 $(function() {
    $( "#datepicker1" ).datepicker();
	 $( "#datepicker2" ).datepicker();
	  $( ".datepicker" ).datepicker();
  });
 
 
 $(function() {
    $( "#statics" ).button({
      icons: {
        primary: "ui-icon-play"
      }     
    });
	$( ".statics" ).button({
      icons: {
        primary: "ui-icon-play"
      }     
    });
	 $( "#setting-save" ).button({
      icons: {
        primary: "ui-icon-check"
      }     
    });
	 $( ".setting-save" ).button({
      icons: {
        primary: "ui-icon-check"
      }     
    });
	$( "#setting-cancel" ).button({
      icons: {
        primary: "ui-icon-cancel"
      }     
    });
	$( ".setting-cancel" ).button({
      icons: {
        primary: "ui-icon-cancel"
      }     
    });
	$( ".user-delete" ).button({
      icons: {
        primary: "ui-icon-close"
      }     
    });
	$( ".user-edit" ).button({
      icons: {
        primary: "ui-icon-gear"
      }     
    });
	$( ".user-search" ).button({
      icons: {
        primary: "ui-icon-search"
      }     
    });
	
	
	$( ".ui_icon_circle_close " ).button({
      icons: {
        primary: "ui-icon-circle-close "
      }     
    });
	$( ".ui_icon_circle_plus " ).button({
      icons: {
        primary: "ui-icon-circle-plus "
      }     
    });
	$( ".ui_icon_person" ).button({
      icons: {
        primary: "ui-icon-person"
      }     
    });
	$( ".upload" ).button({
      icons: {
        primary: "ui-icon-arrowthickstop-1-n"
      }     
    });
	$( ".download" ).button({
      icons: {
        primary: "ui-icon-arrowthickstop-1-s"
      }     
    });
	$( ".return" ).button({
      icons: {
        primary: "ui-icon-arrowreturnthick-1-w"
      }     
    });
	 $( ".setting-save" ).button({
      icons: {
        primary: "ui-icon-check"
      }     
    });
	
	$( ".setting-cancel" ).button({
      icons: {
        primary: "ui-icon-cancel"
      }     
    });
	$( ".ui_icon_gear" ).button({
      icons: {
        primary: "ui-icon-gear"
      }     
    });
  });
 //////////////////////////////////////////
 $(function() {
	$( "#slider-range-t" ).slider({
      range: true,
      min: -20,
      max: 300,
      values: [ 12, 200 ],
      slide: function( event, ui ) {
        $( "#amount-t" ).val(  ui.values[ 0 ] + " C -" + ui.values[ 1 ] +" C");
      }
    });
    $( "#amount-t" ).val( $( "#slider-range-t" ).slider( "values", 0 ) +
      " C -" + $( "#slider-range-t" ).slider( "values", 1 ) +" C");  
  });
  
  
  
  $(function() {
	$( "#slider-range-i" ).slider({
      range: true,
      min: 0,
      max: 1000,
      values: [ 100, 500 ],
      slide: function( event, ui ) {
        $( "#amount-i" ).val(  ui.values[ 0 ] + " mA -" + ui.values[ 1 ] +" mA");
      }
    });
    $( "#amount-i" ).val( $( "#slider-range-i" ).slider( "values", 0 ) +
      " mA -" + $( "#slider-range-i" ).slider( "values", 1 ) +" mA");  
  });
  
  $(function() {
    $( "#slider-range-v" ).slider({
      range: true,
      min: 0,
      max: 300,
      values: [ 30, 100 ],
      slide: function( event, ui ) {
        $( "#amount-v" ).val(  ui.values[ 0 ] + " V -" + ui.values[ 1 ] +" V");
      }
    });	
    $( "#amount-v" ).val( $( "#slider-range-v" ).slider( "values", 0 ) +
      " V -" + $( "#slider-range-v" ).slider( "values", 1 ) +" V");	  
  }); 
  
  $(function() {
    $( "#slider-range-pickdata-t" ).slider({
      range: "min",
      value: 300,
      min: 0,
      max: 3600,
      slide: function( event, ui ) {
        $( "#amount-pickdata-t" ).val( ui.value+" s" );
      }
    });
    $( "#amount-pickdata-t" ).val(  $( "#slider-range-pickdata-t" ).slider( "value" )+" s" );
  });
  $(function() {
    $( "#slider-range-pickdata-v" ).slider({
      range: "min",
      value: 300,
      min: 0,
      max: 3600,
      slide: function( event, ui ) {
        $( "#amount-pickdata-v" ).val( ui.value+" s" );
      }
    });
    $( "#amount-pickdata-v" ).val(  $( "#slider-range-pickdata-v" ).slider( "value" )+" s" );
  });
  $(function() {
    $( "#slider-range-pickdata-i" ).slider({
      range: "min",
       value: 300,
      min: 0,
      max: 3600,
      slide: function( event, ui ) {
        $( "#amount-pickdata-i" ).val( ui.value+" s" );
      }
    });
    $( "#amount-pickdata-i" ).val(  $( "#slider-range-pickdata-i" ).slider( "value" )+" s" );
  });
  
  
