import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TicTacToe {
   int boardwidth = 600 ;
   int boardheight = 700; // 50px for the text panel on the top 
   JFrame frame = new JFrame("Tic Tac Toe");
   JLabel label = new JLabel();
   JLabel scoreLabel = new JLabel(); // Label for displaying the scores
   JPanel panel = new JPanel();
   JPanel boardPanel = new JPanel();
   JPanel bottomPanel = new JPanel();
   JButton [][] board = new JButton[3][3];
   String playerX = "x";
   String playerO = "o";
   String currentPlayer = playerX;
   boolean gameOver = false;
   int turns = 0;
   int scoreX = 0 ;
   int scoreO = 0 ;
   int winScoreLimit = 9 ;
   JButton resetButton = new JButton("Reset");
   TicTacToe (){
       frame.setSize(boardheight, boardwidth);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setLocationRelativeTo(null); // set window on the center of screen 
       frame.setResizable(false);
       frame.setLayout(new BorderLayout());
       frame.setVisible(true);

       label.setBackground(Color.DARK_GRAY);
       label.setForeground(Color.WHITE);
       label.setFont(new Font ("Arial" , Font.ITALIC , 50));
       label.setText("TicTacToe");
       label.setHorizontalAlignment(JLabel.CENTER);
       label.setOpaque(true); //Makes the label background visible.
       
       scoreLabel.setBackground(Color.DARK_GRAY);
       scoreLabel.setForeground(Color.WHITE);
       scoreLabel.setFont(new Font("Arial", Font.PLAIN, 30));
       scoreLabel.setHorizontalAlignment(JLabel.CENTER);
       scoreLabel.setOpaque(true);
       updateScoreLabel(); // Initial score display

       panel.setLayout(new BorderLayout());
       panel.add(label , BorderLayout.NORTH);
       panel.add(scoreLabel,BorderLayout.SOUTH);
       frame.add(panel,BorderLayout.NORTH);

       boardPanel.setLayout(new GridLayout(3,3));
       boardPanel.setBackground(Color.darkGray);
       frame.add(boardPanel);

       bottomPanel.setLayout(new FlowLayout());
       bottomPanel.setBackground(Color.DARK_GRAY);
       frame.add(bottomPanel,BorderLayout.SOUTH);
       resetButton.setFont(new Font("Arial" , Font.PLAIN , 30 ));
       resetButton.setBackground(Color.orange);
       resetButton.setForeground(Color.WHITE);
       resetButton.setFocusable(false);
       bottomPanel.add(resetButton);

       for(int r = 0 ; r < 3 ; r++){
        for(int c = 0 ; c < 3 ; c++){
            JButton tile = new JButton(); // create button for each tile 
            board[r][c] = tile ; // stire button on the board 
            boardPanel.add(tile); // add button to board panel
            // color for the button 
            tile.setBackground(Color.darkGray);
            tile.setForeground(Color.white);
            tile.setFont(new Font("Arial", Font.BOLD , 120));
            tile.setFocusable(false); // disables the button focus 
            // to handel button click 
            tile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                  if(gameOver) return ;
                  JButton tile = (JButton) e.getSource();
                  // if button is empty 
                  if(tile.getText() == ""){
                      tile.setText(currentPlayer);
                      turns++;
                      checkWiner();
                      // switch currentplayer
                      if(!gameOver){
                          currentPlayer = currentPlayer.equals(playerX )? playerO : playerX ;
                          label.setText(currentPlayer + "'s turn.");
                      }  
                  } 

                }

            });
        }
    }


   resetButton.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
        resetGame();
    }
   });
}
   void checkWiner(){
    // horizontal 
    for(int r = 0 ; r <3 ; r ++){
        if(board[r][0].getText() == "") continue;

        if(board[r][0].getText() == board[r][1].getText() && 
        board[r][1].getText() == board[r][2].getText()){
            for(int i = 0 ; i < 3 ; i++){
                setWinner(board[r][i]);
            }
            gameOver = true;
            return ;
        }
     }
     // vertical 
     for(int c = 0 ; c <3 ; c ++){
        if(board[0][c].getText() == "") continue ;

        if(board[0][c].getText() == board[1][c].getText()&&
        board[1][c].getText() == board[2][c].getText()){
            for(int i = 0 ; i < 3 ; i++){
                setWinner(board[i][c]);
            }
            gameOver = true;
            return ;
        }     
     }
     // diagonal
     if(board[0][0].getText() == board[1][1].getText()&& 
     board[1][1].getText() == board[2][2].getText()&&
     board[0][0].getText() != "") {
        for (int i = 0 ; i < 3 ; i++){
         setWinner(board[i][i]); 
        }
        gameOver = true ;
        return ;
        }

           //anti-diagonally
           if (board[0][2].getText() == board[1][1].getText() &&
           board[1][1].getText() == board[2][0].getText() &&
           board[0][2].getText() != "") {
           setWinner(board[0][2]);
           setWinner(board[1][1]);
           setWinner(board[2][0]);
           gameOver = true;
           return;
       }  

       if(turns == 9 ){
         for(int r = 0 ; r < 3 ; r++){
          for(int c =0; c< 3 ; c ++){
            setTie(board[r][c]);
          }
         }
         gameOver = true;
       }

    }
 void setWinner(JButton tile ){
    tile.setForeground(Color.green);
    tile.setBackground(Color.gray);
    label.setText(currentPlayer + " is the winner!");
    resetButton.setText("Play Again");
    // check current player and increment his score 
    if(currentPlayer.equals(playerX)){
        scoreX++;
     } else {
            scoreO++;
        }
        updateScoreLabel(); // update score label 
     if(scoreX == winScoreLimit || scoreO == winScoreLimit){
        gameOver = true ;
        label.setText(currentPlayer + " is the winner!");
        resetButton.setText("New Round");
        scoreX = 0;
        scoreO = 0;
     }
  }

  void setTie(JButton tile){
    tile.setForeground(Color.orange);
    tile.setBackground(Color.gray);
    label.setText("Tie!");
  }
  void resetGame(){
    for(int r =0 ; r < 3 ; r ++){
        for(int c = 0 ; c < 3 ; c++){
            board[r][c].setText("");
            board[r][c].setBackground(Color.darkGray);
            board[r][c].setForeground(Color.WHITE);
        }
    }
    gameOver = false ;
    turns = 0 ;
    currentPlayer = playerX ;
    label.setText("Tic Tac Toe");
    resetButton.setText("Reset");
    if (scoreX == 0 && scoreO == 0) {
        updateScoreLabel();
    }
  }
  void updateScoreLabel() {
    scoreLabel.setText("Score - X: " + scoreX + " | O: " + scoreO);
}

}

