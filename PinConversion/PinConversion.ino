void setup() {
  //turning speed
  pinMode(1, INPUT);
  pinMode(2, INPUT);
  pinMode(3, INPUT);
  pinMode(4, INPUT);
  //main wheel speed
  pinMode(5, INPUT);
  pinMode(6, INPUT);
  pinMode(7, INPUT);
  pinMode(8, INPUT);


  //analog out 
  pinMode(A0, OUTPUT);
  pinMode(A1, OUTPUT);
}

int turnValue = 0;
int speedValue = 0;

void loop() {
  for(int i = 1; i <= 4; i++){
    if(digitalRead(i) == 0){
      turnValue = (turnValue<<1)|0x00000001;
    }else{
      turnValue = (turnValue<<1)|0x00000000;
    }    
  }
}
