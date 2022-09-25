import 'package:flutter/material.dart';
import 'package:test/util/word_find.dart';

class Home_screen extends StatefulWidget {
  @override
  _ClassState createState() => _ClassState();
}

class _ClassState extends State<Home_screen> {
  TextEditingController row = TextEditingController(text: "0");
  TextEditingController column = TextEditingController(text: "0");
  TextEditingController alphCon = TextEditingController(text: "");
  TextEditingController searchWord = TextEditingController(text: "");
  List<List<String>> puzzle = [];
  int rowC = 0;
  int colC = 0;
  List<Latters> lattersList = [];

  @override
  Widget build(BuildContext context) {
    return SafeArea(
        child: Scaffold(
      body: Column(
        mainAxisSize: MainAxisSize.max,
        children: [
          const SizedBox(width: 5, height: 5),
          Padding(
            padding: const EdgeInsets.all(5),
            child: Row(
              crossAxisAlignment: CrossAxisAlignment.center,
              mainAxisAlignment: MainAxisAlignment.center,
              mainAxisSize: MainAxisSize.min,
              children: [
                Expanded(
                  child: TextField(
                    controller: row,
                    maxLength: 2,
                    onChanged: (str) {
                      try {
                        rowC = int.parse(str);
                      } catch (e) {
                        print(e.toString());
                      }

                      if (rowC > 0 && colC > 0) {
                        lattersList.clear();
isGridCreated=false;                        
                        alphCon = TextEditingController(text: "");
                        searchWord = TextEditingController(text: "");
                        setState(() {});
                      }
                    },
                    keyboardType: TextInputType.number,
                    decoration: const InputDecoration(
                        border: OutlineInputBorder(),
                        labelText: "Row",
                        contentPadding: EdgeInsets.only(top: 10.0, left: 5),
                        fillColor: Colors.deepOrange,
                        counterText: ""),
                  ),
                ),
                const SizedBox(width: 5),
                Expanded(
                  child: TextField(
                    controller: column,
                    keyboardType: TextInputType.number,
                    maxLength: 2,
                    onChanged: (str) {
                      try {
                        colC = int.parse(str);
                      } catch (e) {}

                      if (rowC > 0 && colC > 0) {
                        lattersList.clear();
                        isGridCreated=false;
                        alphCon = TextEditingController(text: "");
                        searchWord = TextEditingController(text: "");
                        setState(() {});
                      }
                    },
                    decoration: const InputDecoration(
                        border: OutlineInputBorder(),
                        contentPadding: EdgeInsets.only(top: 10.0, left: 5),
                        labelText: "Colunm",
                        counterText: ""),
                  ),
                ),
              ],
            ),
          ),
          const SizedBox(
            height: 5,
          ),
          Visibility(
            child: Padding(
                child: Row(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Expanded(
                      child: TextField(
                        /*keyboardType: TextInputType.multiline,
                    maxLines: rowC == 0 ? 1 : rowC,*/
                        controller: alphCon,
                        maxLength: rowC * colC == 0 ? 1 : rowC * colC,
                        decoration: InputDecoration(
                            border: const OutlineInputBorder(),
                            contentPadding:
                                const EdgeInsets.only(top: 10.0, left: 5),
                            labelText:
                                "Enter ${rowC * colC} alphabets for grid",
                            fillColor: Colors.deepOrange),
                      ),
                    ),
                    FlatButton(
                        onPressed: () {
                          FocusScope.of(context).unfocus();
                          rowC = int.parse(row.text);
                          colC = int.parse(column.text);
                          if (alphCon.text.length == rowC * colC) {
                            lattersList.clear();
                            alphCon.text.codeUnits.forEach((element) {
                              lattersList.add(Latters(
                                  alphabet: String.fromCharCode(element),
                                  isSelected: false));
                            });
                            //5. Display the grid.
                            create2Darray();
                            isGridCreated=true;
                            //   String.fromCharCode(puzzleText.codeUnits[index])
                            setState(() {});
                          } else {
                            var snackBar = SnackBar(
                              content: Text(
                                  'please enter ${rowC * colC} alphabets for create puzzle'),
                            );
                            ScaffoldMessenger.of(context)
                                .showSnackBar(snackBar);
                          }
                        },
                        child: Container(
                            color: Colors.purple,
                            padding: const EdgeInsets.only(
                                left: 10, right: 10, top: 15, bottom: 15),
                            child: Text("Grid")))
                  ],
                ),
                padding: EdgeInsets.only(left: 5)),
            visible: rowC * colC > 0,
          ),
          const SizedBox(
            height: 5,
          ),
          Visibility(child: Padding(
              child: Row(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Expanded(
                    child: TextField(
                      controller: searchWord,
                      decoration: const InputDecoration(
                          border: OutlineInputBorder(),
                          contentPadding: EdgeInsets.only(top: 10.0, left: 5),
                          labelText: "Enter a word want searched in the grid ",
                          fillColor: Colors.deepOrange),
                    ),
                  ),
                  FlatButton(
                      onPressed: searchFun,
                      child: Container(
                          color: Colors.blueAccent,
                          padding: const EdgeInsets.only(
                              left: 10, right: 10, top: 15, bottom: 15),
                          child: const Text("Search")))
                ],
              ),
              padding: EdgeInsets.only(left: 5)),visible: isGridCreated,),
          Expanded(
            child: Container(
              padding: const EdgeInsets.all(5),
              //width:  400,
              child: Visibility(
                child: lattersList.isEmpty
                    ? Container()
                    : GridView.builder(
                        scrollDirection: Axis.vertical,
                        itemCount: colC * rowC,
                        gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                            crossAxisCount: colC,
                            crossAxisSpacing: 5,
                            mainAxisSpacing: 5),
                        shrinkWrap: true,
                        itemBuilder: (context, index) => Container(
                          color: lattersList[index].isSelected
                              ? Colors.red
                              : Colors.cyanAccent,
                          padding: const EdgeInsets.all(5),
                          child: Text(
                            "${lattersList[index].alphabet}",
                            style: const TextStyle(color: Colors.black),
                          ),
                        ),
                      ),
                visible: colC != 0,
              ),
            ),
          )
        ],
      ),
    ));
  }

  searchFun() {
    FocusScope.of(context).unfocus();
    lattersList.forEach((element) {
      element.isSelected = false;
    });
    String word = searchWord.text;
    WordFind wf = WordFind(puzzle: puzzle);
    final WSolved solved = wf.solvePuzzle(puzzle, [word]);
    print('Found Words!');
    for (var element in solved.found) {
      print('word: ${element.word}, orientation: ${element.orientation}');
      print('column:${element.column}, row:${element.row}');
      if (element.orientation == 0) {
        //horizontal
        int tem = element.row * colC + element.column;
        for (var i = 0; i < element.word.length; i++) {
          lattersList[tem + i].isSelected = true;
        }

        debugPrint("index--- $tem ");
      } else if (element.orientation == 1) {
        //vertical
        int tem = element.row * colC + element.column;
        for (var i = 0; i < element.word.length; i++) {
          lattersList[i * colC + tem].isSelected = true;
        }
        debugPrint("index--- $tem ");
      } else {
        //diagonal
        int tem = element.row * colC + element.column;
        for (var i = 0; i < element.word.length; i++) {
          lattersList[tem + i * colC + i].isSelected = true;
        }
        debugPrint("index--- $tem ");
      }
      setState(() {});
    }
  }
  bool isGridCreated=false;

  void create2Darray() {
    puzzle.clear();
    var puzzleText = alphCon.text.toString();
    var alphabetsList = puzzleText.codeUnits;
    for (var i = 0; i < rowC; i++) {
      puzzle.add([]);
      for (var j = 0; j < colC; j++) {
        puzzle[i].add(String.fromCharCode(alphabetsList[j + i * colC]));
      }
    }
    WordFind wf = WordFind(puzzle: puzzle);
    print(wf.toString());
  }
}

class Latters {
  String alphabet;
  bool isSelected;

  Latters({required this.alphabet, required this.isSelected});
}
