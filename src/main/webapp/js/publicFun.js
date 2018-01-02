
  function getSelectedItem(frm){
    var p_FrmDataLst = frm;

    if (p_FrmDataLst.recordID==null) { 
       //alert("null");
       return null;
    }

    if(p_FrmDataLst.recordID.checked) {
        //alert(p_FrmDataLst.recordID.value);
        return p_FrmDataLst.recordID.value;      
      } 

    for( i=0; i < p_FrmDataLst.recordID.length; i++ ) {    
      if( p_FrmDataLst.recordID[i].checked ) { 
         //alert(p_FrmDataLst.recordID[i].value);
         return p_FrmDataLst.recordID[i].value; 
      }
    }
    return null;
  }
