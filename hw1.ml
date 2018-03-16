(* Student information:

   Enter your name, and if you chose to work in pairs, the name of the
   student you worked with (both students MUST submit the solution to
   myCourses):

   Name: Mohammad Kanny
   McGill ID: 260563654

   Name: Mhd. Said Khalifeh
   McGill ID: 260687985


 *)

(* Notice: by submitting as part of team, you declare that you worked
   together on the solution. Submissions in pairs are allowed to
   foster team work, they have to be developed by both students *)

(* Homework 1 - Questions 2 and 3 *)

(* First, some utility functions and declarations that you can use. Be
   sure to check Ocaml's documentation to find more functions
   available to you.

   You can start checking the documentation at:
   https://caml.inria.fr/pub/docs/manual-ocaml/libref/Pervasives.html
 *)

(* the value of pi *)
let pi : float =  acos ~-.1.0

(* a function to compare floats that allows for some imprecision *)
let cmp n m = abs_float (n -. m) < 0.0001

(* a simple test of positivity *)
let positive a = a > 0.0

(* a function to check if a is multiple of b *)
let is_multiple_of (a : float) (b : float) : bool =
  let m = a /. b in
  cmp (m -. floor m) 0.0

(* a function to check if a is between plus/minus b *)
let abs_btwn a b = a < b && a > ~-.b

(* Question 2: Triangles are the best *)

type side = float

type tr_by_sides = side * side * side

type tr_kind
  = Scalene
  | Equilateral
  | Isosceles

(* Question 2.1 *)
let well_formed_by_sides (a, b, c : tr_by_sides) : bool =  
  if not(positive a && positive b && positive c) then false
  else if a +. b <= c then false
  else if b +. c <= a then false
  else if a +. c <= b then false
  else true ;;


(* Question 2.2 *)
let create_triangle (kind : tr_kind) (area : float) : tr_by_sides = match kind with
| Scalene -> (sqrt(5.0 *. area), sqrt(area), 2.0 *. sqrt(area)) 
| Equilateral -> let x = (sqrt((4.0 *. area)/.(sqrt(3.0)))) in (x, x, x)
| Isosceles -> (sqrt (2.0 *. area), sqrt (2.0 *. area), 2.0 *. sqrt(area) ) ;;

(* Question 2.3 *)
type angle = float

type tr_by_angle = side * side * angle

let well_formed_by_angle (a, b, gamma) : bool =
  (positive a && positive b && positive gamma) &&
    (not (is_multiple_of gamma pi))

let sides_to_angle (a, b, c : tr_by_sides) : tr_by_angle option =
  if well_formed_by_sides (a, b, c) then
    Some (a, b, acos((a*.a +. b*.b -. c*.c)/.(2.0 *. a *. b)))
  else None

let angle_to_sides (a, b, gamma) =
  if well_formed_by_angle(a, b, gamma) then
    Some (a, b, (sqrt(a*.a +. b*.b -. (cos gamma)*.2.0*.a*.b)))
  else None


(* Now that you implemented Q2.2 and saw the new representation of
   triangles by two sides and the angle between them, also ponder on
   how one represents Q2.2 using this representation. The idea is to
   think about how the representation helps make illegal states hard
   to represent and how easy and hard it is to implement the
   algorithm. *)

(* Question 3: Flexing recursion and lists *)

let even (n : int) : bool = n mod 2 = 0;;

(* Question 3.1 *)
let evens_first (l : int list) : int list = 
  let rec sorting l l1 = match l with
    | [] -> l1
    | first :: last -> 
      if even first then first :: sorting last l1
      else sorting last (l1 @ [first])
  in
  let l1 = [] in
  sorting l l1;;
let next_val (l : int list): bool = match l with 
  | [] -> false
  | first :: last -> even first;;

let ex_1 = evens_first [7 ; 5 ; 2; 4; 6; 3; 4; 2; 1]
(* val ex_1 : int list = [2; 4; 6; 4; 2; 7; 5; 3; 1] *)

(* Question 3.2 *)
  
let even_streak (l : int list) : int = 
  let rec sorting ( l : int list) ( l1 : int) ( l2 : int) : int = match l with
    | [] -> l1      
    | first :: last  -> 
      if even first && next_val last then sorting last l1 (l2 + 1)
      else if even first then if (l2 + 1)>l1 then sorting last (l2 + 1) 0 else sorting last l1 0
      else sorting last l1 0                                                   
      
  in


let ex_2 = even_streak [7; 2; 4; 6; 3; 4; 2; 1]

(* val ex_2 : int = 3 *)


(* Question 3.3 *)

type nucleobase = A | G | C | T;;

let reverse_list (l : 'a list) : 'a list =
    let rec ref (l : 'a list) (l1 : 'a list): 'a list = match l with
       | [] -> l1
       | s :: s1 -> ref s1 (s::l1)
    in
    ref l [];;

let next_nuc (l : nucleobase list) (s : nucleobase): bool = match l with 
  | [] -> false
  | t1 :: t2 -> t1 = s;;


  (*when next nuc is not the current nuc then update counter to 0 and add to the compressed list*)
let compress (l : nucleobase list) : (int * nucleobase) list =
  let rec sorting (l : nucleobase list) (l1 : (int * nucleobase) list)  ( base : nucleobase)( counter : int)  : ( int * nucleobase) list = match l with
    | [] -> l1      
    | t1 :: t2  -> 
      if base = t1 then if next_nuc t2 base then sorting t2 l1 base (counter + 1) else sorting t2 (((counter + 1), base):: l1) base 0 
      else if next_nuc t2 t1 then sorting t2 l1 t1 (counter + 1)
      else sorting t2((1,t1):: l1) t1 0 
           
  in
  reverse_list (sorting l [] A 0);;

let rec decompressor (l : nucleobase list ) (l1 : nucleobase ) (counter : int) : nucleobase list =
  if counter = 0 then l
  else decompressor (l1 :: l) l1 (counter - 1);; 
  
let decompress (l : (int * nucleobase) list) : nucleobase list =
  let rec operator (l : (int * nucleobase) list) (l1 : nucleobase list ) = match l with
    | [] -> l1
    | (s1,s2) :: s3 -> operator s3 (decompressor l1 s2 s1) 
  in 
  reverse_list (operator l [] );;
     

let sample_dna : nucleobase list = [A;A;A;A;G;G;A;T;T;T;C;T;C]

let ex_3 = compress sample_dna

let ex_4 = decompress ex_3

let res_3_4 = sample_dna = ex_4 (* This should be true if everything went well *)
