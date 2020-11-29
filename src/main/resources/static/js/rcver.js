$(function () {

	var coll = document.getElementsByClassName("collapsible");
var i;

for (i = 0; i < coll.length; i++) {
  coll[i].addEventListener("click", function() {
    this.classList.toggle("active");
    var content = this.nextElementSibling;
    if (content.style.maxHeight){
      content.style.maxHeight = null;
    } else {
      content.style.maxHeight = content.scrollHeight + "px";
    }
  });
}
	
	//Handler for when an action button is clicked for a candidate
	$("[name^=candidateActionButton]").click(function() {
		console.log('test');
		if(!$(this).hasClass("candidate-selected")){
			$(this).addClass("candidate-selected");
			addCandidate($(this).text())
		}else{
			$(this).removeClass("candidate-selected");
			removeCandidate($(this).text())
		}
	  
	});
	
	function addCandidate(candidate) {	
	console.log("Adding for candidate " + candidate);	
		$("[name^=rankedCandidates]").each(function( index ) {
			console.log( index + ": " + $( this ).val() );
			if($( this ).val() === ''){
				console.log("Writing " + candidate + " at index " + index);
				$( this ).val(candidate);
				return false;
			}  			
		});
  		
	}
		
	function removeCandidate(candidate) {	
		console.log("Adding for candidate " + candidate);	
		//first we make a list of the existing ballot inputs
		currentBallot = [];
		$("[name^=rankedCandidates]").each(function( index ) {
			if($(this).val() != candidate){
				currentBallot.push($(this).val());
			}
		});
		//then we identify the remaining elements to shift up
		$("[name^=rankedCandidates]").each(function( index ) {
			console.log( index + ": " + $( this ).val() );
			if(index > currentBallot.length){
				$( this ).val('');
				return false;
			}else{
				$( this ).val(currentBallot[index]);
			}  			
		});
	}
       
});


    
