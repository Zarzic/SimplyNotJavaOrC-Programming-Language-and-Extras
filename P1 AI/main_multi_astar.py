#gets the path of the current node as an input and bactracks through its
#parent node until it reaches the starting node
from ordered_set import OrderedSet
import copy

def Path(currentNode):
  path = []
  current = currentNode
  while current is not None:
    path.append(current.position)
    current = current.parent
  path = path[::-1]
  return path

set_path = OrderedSet()
depth = 0
fringe = 0
big_depth = 0
op = 0
#uses astar serch algorithm to find the shortest path to the goal
def aStar_multi(maze, goals):
  row = len(maze)
  column = len(maze[0])

  global depth
  global fringe
  global big_depth
  global op
  start = (0, 0)
  #creates a nested loop to search through the maze searching for 'P'
  for r in range(row):
    for c in range(column):
      if maze[r][c] == 'P':
        start = (r, c)
        break
  #uses Node class construction method to create a starting node
  startNode = Node1(None, start)
  startNode.g = 0
  startNode.h = 0
  startNode.f = 0

  #uses Node class construction method to create a goal node and stores each goal node in a list
  goalNodes = []
  for g in goals:
    goalNode = Node1(None, g)
    goalNode.g = 0
    goalNode.h = 0
    goalNode.f = 0
    goalNodes.append(goalNode)

  openList = []
  closedList = []

  openList.append(startNode)

  directions = [[1, 0], [0, 1], [0, -1], [-1, 0]]
  goalsReached = set()

  while len(openList) > 0 and len(goals) > len(goalsReached):
    currentNode = openList[0]
    currentIndex = 0
    #as long as item has a value in openList, iterate through the list
    #if item is less than the current node

    if (len(openList)) > fringe:
      fringe = len(openList)
    for index, item in enumerate(openList):
      if item.f < currentNode.f:
        currentNode = item
        currentIndex = index
    openList.pop(currentIndex)
    depth = currentNode.depth
    closedList.append(currentNode)
    #if the current nodes position is in a goal, print the path and added
    #to the list of goals reached
    if currentNode.position in goals:
      goalsReached.add(currentNode.position)
      x = Path(currentNode)
      for bb in x:
        set_path.add(bb)
      if currentNode.depth > big_depth:
        big_depth = currentNode.depth

    #if goalsReached is the same as length of goals, print and return statments
    if len(goalsReached) == len(goals):
      return
    #creates a list of children for each valid direction and adds it to a list
    children = []
    for dir in directions:
      nodePos = (currentNode.position[0] + dir[0], currentNode.position[1] + dir[1])

      if (nodePos[0] < 0 or nodePos[0] >= row or nodePos[1] < 0 or nodePos[1] >= column or maze[nodePos[0]][nodePos[1]] == '%'):
        continue
      next = Node1(currentNode, nodePos)
      children.append(next)
    op = op + 1
    for child in children:
      if len([n for n in closedList if n == child]) > 0:
        continue

      child.g = currentNode.g + 1
      child.h = min([abs(child.position[0] - goal.position[0]) +abs(child.position[1] - goal.position[1]) for goal in goalNodes])
      child.f = child.g + child.h
      child.depth = depth + 1

      if len([i for i in openList if child == i and child.g >= i.g]) > 0:
        continue
      openList.append(child)



#Seperate Node Class for A*
class Node1:

  def __init__(self, parent=None, position=None):
    self.parent = parent
    self.position = position

    self.g = 0
    self.h = 0
    self.f = 0
    self.depth = 0

  def __eq__(self, other):
    return self.position == other.position


#A* maze Setup
with open('mazeFiles/trickySearch.lay') as file:
  maze = []
  for line in file:
    maze.append([i for i in line.strip("\n")])

#A* Goals Findings
goals = []
for r in range(len(maze)):
  for c in range(len(maze[0])):
    if maze[r][c] == '.':
      goals.append((r, c))

#####################################################################################################################################
#####################################################################################################################################
#####################################################################################################################################
# Changed Area Stuff for Output
######################################################################################################################################
#####################################################################################################################################
#####################################################################################################################################
file1 = open("mazeFiles/trickySearch.lay", "r")
lines = file1.read().splitlines()
lis = []
for x in lines:
    lis.append(list(x))

print("")
print("MAZE:")
print("")
for xx in lis:
  print(xx)
print("")
print("")

ans2 = aStar_multi(maze, goals)
yah = goals
reversed_points = [item[::-1] for item in yah]
corrected_points = []
for bb in reversed_points:
  y = list(bb)
  y[1] = abs(y[1] - (len(maze) - 1))
  cc = tuple(y)
  corrected_points.append(cc)
print("Goals Label (G): " + str(corrected_points))

yah2 = set_path
reversed_points2 = [item2[::-1] for item2 in yah2]
corrected_points2 = []
for bb2 in reversed_points2:
  y2 = list(bb2)
  y2[1] = abs(y2[1] - (len(maze) - 1))
  cc2 = tuple(y2)
  corrected_points2.append(cc2)

print("Solution Moves (X,Y) (Bottom left is (0,0)): " + str(corrected_points2))
print("Max Depth = " + str(depth))
print("Max Fringe = " + str(fringe))
print("Max Expanded Nodes = " + str(op))
print("Path Cost: " + str(len(corrected_points2) - 1))