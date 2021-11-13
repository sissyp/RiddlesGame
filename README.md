# RiddlesGame

This repository contains the application code for my Bachelor thesis.

RiddlesGame is an Android application on the Ethereum private blockchain with the 
use of smart contracts and IPFS. It is a game that offers a range of mathematical riddles at 
three levels of difficulty; Easy, Medium and Hard. Users can solve riddles from any 
of the aforementioned levels and track their progress in the game. A ranking system is used 
to keep track of the top three players. The riddles are stored on IPFS. In order to play the 
game users are required to have a MetaMask account (software cryptocurrency wallet), so 
as to receive rewards and make purchases in the application.

The application’s smart contracts are written in solidity using the Remix IDE. The interface is 
designed using Android Studio and the programming language is Java. Finally, the optimal 
solution for handling the riddle’s files was IPFS.
Each riddle has its own file that needs to be stored in IPFS so that the riddle can appear 
on the game. By storing a file on IPFS a hash is produced, which is then stored on the 
Blockchain. When a user chooses to solve a riddle the unique url that correlates 
to it is extracted. This process is shown on the diagram bellow.

![overview of the application]()

Wrapper classes in Java were used to call the application’s smart contracts. In order to do 
this solidity’s solcjs compiler and the web3j Java Library were used. After creating the wrapper 
classes the smart contracts were called using their address and the user’s credentials.

The application was created using smart contracts. Two of the application’s smart contracts; 
Ownable and SafeMath, are from the OpenZeppelin Library. Ownable is used as an access 
modifier to guarantee that only the owners of a contract and can do administrative tasks on 
it. SafeMath, as the name suggests, is used to avoid underflow and overflow when 
performing mathematical operations in the application’s code. Another smart contract, 
PlayerContract has all the functions regarding the creation of an avatar, the retrieval of its 
attributes and the modifier that correlates the avatar’s id with the user’s account. 
RiddlesContract, has the function for the creation of the riddles. In this smart contract
only the riddle’s id and it’s hash from IPFS are stored. The solutions of the riddle’s are not stored 
in the smart contract. RiddlesHelperContract uses both PlayerContract and RiddleContract to 
create functions that check whether a user can level up in the game and if they can use a 
hint.

