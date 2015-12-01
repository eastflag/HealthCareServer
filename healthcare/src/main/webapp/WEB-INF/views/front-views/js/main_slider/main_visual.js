$(document).ready(function(){

$('#prev').css('display','none');

// location
sliderArea = new Swipe(document.getElementById('sliderArea'), {
  speed: 400,
  callback: function(event,index,elem) {
	setTab(selectors[index]);

    var slideNum = $('#sliderArea li').length - 1;

    if(index == 0){
        $('#prev').css('display','block');
    }else{
        $('#prev').css('display','block');
    }
    if(index == slideNum){
        $('#next').css('display','block');
    }else{
        $('#next').css('display','block');
    }
      
  }
}),
selectors = document.getElementById('circleLocation').children;

for (var i = 0; i < selectors.length; i++) {
  var elem = selectors[i];
  elem.setAttribute('data-tab', i);
  elem.onclick = function(e) {
    e.preventDefault();
    setTab(this);	
    sliderArea.slide(parseInt(this.getAttribute('data-tab'),10),0);	
  }
}

function setTab(elem) {
  for (var i = 0; i < selectors.length; i++) {
    selectors[i].className = selectors[i].className.replace('on',' ');
  }
  elem.className += ' on';
}

// url bar hiding
(function() {
    
  var win = window,
      doc = win.document;

  // If there's a hash, or addEventListener is undefined, stop here
  if ( !location.hash || !win.addEventListener ) {

    //scroll to 1
    window.scrollTo( 0, 1 );
    var scrollTop = 1,

    //reset to 0 on bodyready, if needed
    bodycheck = setInterval(function(){
      if( doc.body ){
        clearInterval( bodycheck );
        scrollTop = "scrollTop" in doc.body ? doc.body.scrollTop : 1;
        win.scrollTo( 0, scrollTop === 1 ? 0 : 1 );
      } 
    }, 15 );

    if (win.addEventListener) {
      win.addEventListener("load", function(){
        setTimeout(function(){
          //reset to hide addr bar at onload
          win.scrollTo( 0, scrollTop === 1 ? 0 : 1 );
        }, 0);
      }, false );
    }
  }

})();

});
