import { Directive, HostListener, ElementRef, HostBinding } from '@angular/core';

@Directive({
  selector: '[appHeighlight]'
})
export class HeighlightDirective {
@HostListener('mouseenter') mouseover(){
this.backGroudColor='green';
}
@HostListener('mouseleave') mouseleaver(){
  this.backGroudColor='red';
  }
@HostBinding('style.backgroundColor') get setColor(){

return this.backGroudColor;
};
private backGroudColor='white';
  //constructor(private elementRef: ElementRef ) { 
//this.elementRef.nativeElement.style.backgroundColor='green';
constructor(){  
}

}
