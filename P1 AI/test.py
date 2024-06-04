import matplotlib.pyplot as plt

class Node:
    def __init__(self, parent=None, position=None):
        self.parent = parent
        self.position = position

        self.g = 0
        self.h = 0
        self.f = 0
        
    def __eq__(self, other):
        return self.position == other.position

# This function returns the path of the search
def Path(curr_node):
    path = []
    curr = curr_node
    while curr is not None:
        path.append(curr.position)
        curr = curr.parent
    # Return reversed path as we need to show from start to end path
    path = path[::-1]
    return path


def aStar(maze, goals):    
    # Size of maze
    row = len(maze)
    col = len(maze[0])
    
    # Find starting point
    start = (0, 0)
    for r in range(row):
        for c in range(col):
            if maze[r][c] == 'P':
                start = (r, c)
                break

    # Create start node
    start_node = Node(None, start)
    start_node.g = 0
    start_node.h = 0
    start_node.f = 0
    
    # Create goal nodes
    goal_nodes = []
    for goal in goals:
        goal_node = Node(None, goal)
        goal_node.g = 0
        goal_node.h = 0
        goal_node.f = 0
        goal_nodes.append(goal_node)

    # Create not_visited and visited lists to keep track of nodes
    not_visited = []
    visited = []
    
    # Add the start node
    not_visited.append(start_node)
    
    # Adding a stop condition. This is to avoid any infinite loop.
    iterations = 0
    max_iterations = (len(maze) // 2) ** 10
    
    # East, South, North, West
    directions = [[1, 0], [0, 1], [0, -1], [-1, 0]]

    # Loop until all goals are reached    
    goals_reached = set()
    while len(goals_reached) < len(goals) and len(not_visited) != 0:
        
        # Every time any node is referred from the not_visited list, the counter of iterations is incremented
        iterations = iterations + 1    
        
        # Get the current node
        curr_node = not_visited[0]
        curr_index = 0
        
        # Compare f cost and select the lowest cost node
        for index, item in enumerate(not_visited):
            if item.f < curr_node.f:
                curr_node = item
                curr_index = index
                
        # Stop from going into an infinite loop
        if iterations > max_iterations:
            print("Too many iterations!")
            return Path(curr_node)

        # Remove current node and add it to the visited list
        not_visited.pop(curr_index)
        visited.append(curr_node)

        # Check if the current node is a goal
        if curr_node.position in goals:
            goals_reached.add(curr_node.position)

        # Stop the search if all goals are reached
        if len(goals_reached) == len(goals):
            print("Goals reached:", goals_reached)
            return Path(curr_node)

        # Generate nodes from all neighbors
        children = []
        for d in directions:
            node_position = (curr_node.position[0] + d[0], curr_node.position[1] + d[1])
            
            # Check if the node is valid and isn't a wall; otherwise, skip
            if (node_position[0] < 0 or node_position[0] >= row or node_position[1] < 0 or node_position[1] >= col 
                or maze[node_position[0]][node_position[1]] == '%'):
                continue
        
            # Create neighbor node (parent, child)
            next_node = Node(curr_node, node_position)
            children.append(next_node)

        # Loop through child nodes
        for child in children:            
            # Skip if the child is already on the visited list 
            if len([visited_child for visited_child in visited if visited_child == child]) > 0:
                continue

            # Create the g, h, and f values
            child.g = curr_node.g + 1
            # Heuristic costs calculated here; this is using Manhattan distance
            child.h = min([abs(child.position[0] - goal.position[0]) + abs(child.position[1] - goal.position[1]) for goal in goal_nodes]) 
            child.f = child.g + child.h

            # Skip if the child is already on the not_visited list and the g cost is already lower
            if len([i for i in not_visited if child == i and child.g > i.g]) > 0:
                continue

            # Add the child to the not_visited list
            not_visited.append(child)


def draw_maze(maze, path=None):
    row = len(maze)
    col = len(maze[0])
    fig, ax = plt.subplots()
    ax.set_aspect("equal")
    ax.set_xlim(0, col)
    ax.set_ylim(0, row)
    ax.set_xticks([])
    ax.set_yticks([])
    for r in range(row):
        for c in range(col):
            if maze[r][c] == "#":
                rect = plt.Rectangle((c, row - r - 1), 1, 1, facecolor="black")
                ax.add_patch(rect)
            elif maze[r][c] == "%":
                rect = plt.Rectangle((c, row - r - 1), 1, 1, facecolor="gray")
                ax.add_patch(rect)
            elif maze[r][c] == ".":
                rect = plt.Rectangle((c, row - r - 1), 1, 1, facecolor="red")
                ax.add_patch(rect)
            elif maze[r][c] == "P":
                rect = plt.Rectangle((c, row - r - 1), 1, 1, facecolor="green")
                ax.add_patch(rect)
    if path:
        path_x = [node[1] + 0.5 for node in path]
        path_y = [row - node[0] - 0.5 for node in path]
        ax.plot(path_x, path_y, color="blue", linewidth=2)
    plt.show()


with open('mazeFiles/tinySearch.lay') as file:
    maze = []
    for line in file:
        maze.append([i for i in line.strip("\n")])

goals = []
for r in range(len(maze)):
    for c in range(len(maze[0])):
        if maze[r][c] == '.':
            goals.append((r, c))

path = aStar(maze, goals)
if path is not None:
    print("Path:", path)
    draw_maze(maze, path)
